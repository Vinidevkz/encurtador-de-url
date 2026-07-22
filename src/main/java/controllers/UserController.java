package controllers;

import dtos.LoginDTO;
import dtos.TokenResponseDTO;
import dtos.UserDTO;
import entities.User;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AuthenticationService;
import service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@Validated @RequestBody UserDTO userDTO) throws BadRequestException {
        return authenticationService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public TokenResponseDTO loginUser(@Validated @RequestBody LoginDTO loginDTO) throws Exception{
        return authenticationService.login(loginDTO);
    }
}
