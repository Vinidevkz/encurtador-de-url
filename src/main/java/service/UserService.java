package service;

import entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repositories.UserRepository;

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

}
