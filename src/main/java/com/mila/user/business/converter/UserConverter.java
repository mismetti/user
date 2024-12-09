package com.mila.user.business.converter;

import com.mila.user.business.dto.AddressDTO;
import com.mila.user.business.dto.PhoneDTO;
import com.mila.user.business.dto.UserDTO;
import com.mila.user.infrastructure.entity.Address;
import com.mila.user.infrastructure.entity.Phone;
import com.mila.user.infrastructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    public User toUser(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(toAddressList(userDTO.getAddresses()))
                .phones(toPhoneList(userDTO.getPhones()))
                .build();
    }

    public List<Address> toAddressList(List<AddressDTO> addressDTOS) {
        return addressDTOS.stream().map(this::toAddress).toList();
    }

    public Address toAddress(AddressDTO addressDTO) {
        return Address.builder()
                .streetLine1(addressDTO.getStreetLine1())
                .number(addressDTO.getNumber())
                .city(addressDTO.getCity())
                .streetLine2(addressDTO.getStreetLine2())
                .zipcode(addressDTO.getZipcode())
                .state(addressDTO.getState())
                .build();
    }

    public List<Phone> toPhoneList(List<PhoneDTO> phoneDTOS) {
        return phoneDTOS.stream().map(this::toPhone).toList();
    }

    public Phone toPhone(PhoneDTO phoneDTO) {
        return Phone.builder()
                .phonenumber(phoneDTO.getPhonenumber())
                .ddd(phoneDTO.getDdd())
                .ddi(phoneDTO.getDdi())
                .build();
    }

/////

    public UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(toAddressListDTO(user.getAddresses()))
                .phones(toPhoneListDTO(user.getPhones()))
                .build();
    }

    public List<AddressDTO> toAddressListDTO(List<Address> address) {
        return address.stream().map(this::toAddressDTO).toList();
    }

    public AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .id(address.getId())
                .streetLine1(address.getStreetLine1())
                .number(address.getNumber())
                .city(address.getCity())
                .streetLine2(address.getStreetLine2())
                .zipcode(address.getZipcode())
                .state(address.getState())
                .build();
    }

    public List<PhoneDTO> toPhoneListDTO(List<Phone> phones) {
        return phones.stream().map(this::toPhoneDTO).toList();
    }

    public PhoneDTO toPhoneDTO(Phone phone) {
        return PhoneDTO.builder()
                .id(phone.getId())
                .phonenumber(phone.getPhonenumber())
                .ddd(phone.getDdd())
                .ddi(phone.getDdi())
                .build();
    }

    public User updateUser(UserDTO userDTO, User entity) {
        return User.builder()
                .name(userDTO.getName() != null ? userDTO.getName() : entity.getName())
                .id(entity.getId())
                .password(userDTO.getPassword() != null ? userDTO.getPassword() : entity.getPassword())
                .email(userDTO.getEmail() != null ? userDTO.getEmail() : entity.getEmail())
                .addresses(entity.getAddresses())
                .phones(entity.getPhones())
                .build();

    }

    public Address updateAddress(AddressDTO dto, Address entity){
        return Address.builder()
                .id(entity.getId())
                .streetLine1(dto.getStreetLine1() != null ? dto.getStreetLine1() : entity.getStreetLine1())
                .streetLine2(dto.getStreetLine2() != null ? dto.getStreetLine2() : entity.getStreetLine2())
                .number(dto.getNumber() != null ? dto.getNumber() : entity.getNumber())
                .city(dto.getCity() != null ? dto.getCity() : entity.getCity())
                .state(dto.getState() != null ? dto.getState() :  entity.getState())
                .zipcode(dto.getZipcode() != null ? dto.getZipcode() : entity.getZipcode())
                .build();
    }

    public Phone updatePhone(PhoneDTO dto, Phone entity){
        return Phone.builder()
                .id(entity.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .ddi(dto.getDdi() != null ? dto.getDdi() : entity.getDdi())
                .phonenumber(dto.getPhonenumber() != null ? dto.getPhonenumber() : entity.getPhonenumber())
                .build();
    }

    public Address toAddressEntity(AddressDTO dto, Long idUser){
        return Address.builder()
                .streetLine1(dto.getStreetLine1())
                .city(dto.getCity())
                .zipcode(dto.getZipcode())
                .streetLine2(dto.getStreetLine2())
                .state(dto.getState())
                .number(dto.getNumber())
                .user_id(idUser)
                .build();
    }

    public Phone toPhoneEntity(PhoneDTO dto, Long idUser){
        return Phone.builder()
                .phonenumber(dto.getPhonenumber())
                .ddd(dto.getPhonenumber())
                .user_id(idUser)
                .build();
    }
}
