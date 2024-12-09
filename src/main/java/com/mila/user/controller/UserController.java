package com.mila.user.controller;

import com.mila.user.business.UserService;
import com.mila.user.business.dto.AddressDTO;
import com.mila.user.business.dto.PhoneDTO;
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
    public ResponseEntity<UserDTO> searchUserByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(userService.searchUserByEmail(email));
    }

    @DeleteMapping("{email}")
    public ResponseEntity<Void> deleteUserByEmail (@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUserData(@RequestBody UserDTO dto,
                                                  @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(userService.refreshUserData(token, dto));
    }

    @PutMapping("/address")
    public ResponseEntity<AddressDTO> updateAddress(@RequestBody AddressDTO dto,
                                                    @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updateAddress(id, dto));
    }

    @PutMapping("/phone")
    public ResponseEntity<PhoneDTO> updatePhone(@RequestBody PhoneDTO dto,
                                                  @RequestParam("id") Long id){
        return ResponseEntity.ok(userService.updatePhone(id, dto));
    }

    @PostMapping("/address")
    public ResponseEntity<AddressDTO> registerAddress(@RequestBody AddressDTO dto,
                                                      @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(userService.insertAddress(token,dto));
    }

    @PostMapping("/phone")
    public ResponseEntity<PhoneDTO> registerPhone(@RequestBody PhoneDTO dto,
                                                      @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(userService.insertPhone(token,dto));
    }
}
