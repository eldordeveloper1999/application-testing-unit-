package uz.pdp.testingdemo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.testingdemo.entity.User;
import uz.pdp.testingdemo.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public HttpEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public HttpEntity save(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalStateException(user.getName()+ "is already taken");
        }

        userRepository.save(user);

        return ResponseEntity.ok("Saved ...");
    }
}
