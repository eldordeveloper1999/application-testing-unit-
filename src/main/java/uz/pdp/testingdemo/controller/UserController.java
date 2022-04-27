package uz.pdp.testingdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import uz.pdp.testingdemo.entity.User;
import uz.pdp.testingdemo.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {


    @Autowired
    UserService userService;

    @GetMapping
    public HttpEntity getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public HttpEntity saveUser(@RequestBody User user) {
        return userService.save(user);
    }
}
