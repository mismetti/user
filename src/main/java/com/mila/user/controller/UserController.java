package com.mila.user.controller;

import com.mila.user.business.UserService;
import com.mila.user.business.dto.UserDTO;
import com.mila.user.infrastructure.entity.User;
import com.mila.user.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(),
                        userDTO.getPassword())
        );
        return "bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping
    public ResponseEntity<User> searchUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }

    @DeleteMapping("{email}")
    public ResponseEntity<Void> deleteUserByEmail (@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

}
