package com.vini.shorter.controllers;

import com.vini.shorter.dtos.UserDTO;
import com.vini.shorter.entities.User;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vini.shorter.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Validated @RequestBody UserDTO userDTO) throws BadRequestException {
        User user =  userService.saveUser(userDTO);

        return ResponseEntity.ok().body(user);
    }


}
