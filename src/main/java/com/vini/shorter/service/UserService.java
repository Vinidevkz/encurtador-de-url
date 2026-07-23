package com.vini.shorter.service;

import com.vini.shorter.dtos.UserDTO;
import com.vini.shorter.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.vini.shorter.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    public User saveUser(UserDTO userDTO){

        User user = new User(userDTO.name(), userDTO.email(), userDTO.password());

        try {
            User savedUser = userRepository.save(user);
            return savedUser;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            throw  new RuntimeException(e.getMessage());
        }
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

}
