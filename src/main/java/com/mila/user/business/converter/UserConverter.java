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

    public User toUser(UserDTO userDTO){
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

    public Address toAddress (AddressDTO addressDTO){
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

    public Phone toPhone(PhoneDTO phoneDTO){
        return Phone.builder()
                .phonenumber(phoneDTO.getPhonenumber())
                .ddd(phoneDTO.getDdd())
                .ddi(phoneDTO.getDdi())
                .build();
    }

/////

    public UserDTO toUserDTO(User user){
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

    public AddressDTO toAddressDTO (Address address){
        return AddressDTO.builder()
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

    public PhoneDTO toPhoneDTO(Phone phone){
        return PhoneDTO.builder()
                .phonenumber(phone.getPhonenumber())
                .ddd(phone.getDdd())
                .ddi(phone.getDdi())
                .build();
    }


}
