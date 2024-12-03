package com.mila.user.business;

import com.mila.user.business.converter.UserConverter;
import com.mila.user.business.dto.UserDTO;
import com.mila.user.infrastructure.entity.User;
import com.mila.user.infrastructure.exceptions.ConflictException;
import com.mila.user.infrastructure.exceptions.ResourceNotFoundException;
import com.mila.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;

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

    public User searchUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("email não encontrado " + email));
    }

    public void deleteUserByEmail(String email){
        userRepository.deleteByEmail(email);
    }
}




