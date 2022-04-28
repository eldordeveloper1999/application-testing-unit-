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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public HttpEntity getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    public HttpEntity save(User user) {
        Boolean existsByUsername = userRepository.existsByUsername(user.getUsername());

        if (existsByUsername) {
            throw new IllegalStateException(user.getName()+ "is already taken");
        }

        userRepository.save(user);

        return ResponseEntity.ok("Saved ...");
    }


    public HttpEntity update(User user) {
        userRepository.save(user);
        return ResponseEntity.ok("Updated ...");
    }

    public HttpEntity deleteUser(Integer id) {
        userRepository.existsById(id);

        userRepository.deleteById(id);
        return ResponseEntity.ok("Deleted ...");
    }

}
