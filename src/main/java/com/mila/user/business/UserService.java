package com.mila.user.business;

import com.mila.user.business.converter.UserConverter;
import com.mila.user.business.dto.AddressDTO;
import com.mila.user.business.dto.PhoneDTO;
import com.mila.user.business.dto.UserDTO;
import com.mila.user.infrastructure.entity.Address;
import com.mila.user.infrastructure.entity.Phone;
import com.mila.user.infrastructure.entity.User;
import com.mila.user.infrastructure.exceptions.ConflictException;
import com.mila.user.infrastructure.exceptions.ResourceNotFoundException;
import com.mila.user.repository.AddressRepository;
import com.mila.user.repository.PhoneRepository;
import com.mila.user.repository.UserRepository;
import com.mila.user.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AddressRepository addressRepository;
    private final PhoneRepository phoneRepository;

    @Transactional
    public UserDTO saveUser(UserDTO userDTO){
        existsByEmail(userDTO.getEmail());
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = userConverter.toUser(userDTO);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }

    public void existsByEmail(String email) {
        try {
            boolean exists = checkExistantEmail(email);
            if (exists) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado " + e.getCause());
        }
    }

    private boolean checkExistantEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public UserDTO searchUserByEmail(String email){
        try {
            return userConverter.toUserDTO(
                    userRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email não encontrado " + email)));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("email nao encontrado");
        }
    }


    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }

    public UserDTO refreshUserData(String token,UserDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()):null);
        User userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email not found"));

        User user = userConverter.updateUser(dto , userEntity);

        return userConverter.toUserDTO(userRepository.save(user));
    }

    public AddressDTO updateAddress(Long idAddress, AddressDTO addressDTO) {

        Address entity = addressRepository.findById(idAddress).orElseThrow(() ->
                new ResourceNotFoundException("id nao encontrado " + idAddress));
        Address address = userConverter.updateAddress(addressDTO, entity);
        return userConverter.toAddressDTO(addressRepository.save(address));
    }

    public PhoneDTO updatePhone(Long idPhone, PhoneDTO phoneDTO) {
        Phone entity = phoneRepository.findById(idPhone).orElseThrow(() ->
                new ResourceNotFoundException("id nao encontrado " + idPhone));
        Phone phone = userConverter.updatePhone(phoneDTO, entity);
        return userConverter.toPhoneDTO(phoneRepository.save(phone));

    }

    public AddressDTO insertAddress(String token, AddressDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email nao encontrado " + email));

        Address address = userConverter.toAddressEntity(dto, user.getId());
        Address addressEntity = addressRepository.save(address);
        return userConverter.toAddressDTO(addressEntity);
    }

    public PhoneDTO insertPhone(String token, PhoneDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email nao encontrado " + email));

        Phone phone = userConverter.toPhoneEntity(dto, user.getId());
        return userConverter.toPhoneDTO(phoneRepository.save(phone));

    }
}




