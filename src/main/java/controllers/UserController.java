package controllers;

import entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/v1/shorter/user")
public class UserController {

    @GetMapping
    public ResponseEntity<User> getUser(Long id){
        try {

        }catch (Exception e){
            return ResponseEntity.notFound();
        }
    }
}
