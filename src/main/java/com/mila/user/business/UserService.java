package com.mila.user.business;

import com.mila.user.business.converter.UserConverter;
import com.mila.user.business.dto.UserDTO;
import com.mila.user.infrastructure.entity.User;
import com.mila.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserDTO saveUser(UserDTO userDTO){
        User user = userConverter.toUser(userDTO);
        user = userRepository.save(user);
        return userConverter.toUserDTO(user);
    }

}
