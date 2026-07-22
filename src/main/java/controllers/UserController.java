package controllers;

import dtos.UserDTO;
import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import service.UserService;

@RestController("/v1/shorter/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> registerUser(UserDTO userDTO){
        User user = new User(userDTO.name(), userDTO.email(), userDTO.password());

        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
