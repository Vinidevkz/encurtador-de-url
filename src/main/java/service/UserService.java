package service;

import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;

    public void saveUser(User user){
        try {
            userRepository.save(user);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

}
