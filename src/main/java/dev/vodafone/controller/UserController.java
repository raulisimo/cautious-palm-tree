package dev.vodafone.controller;

import dev.vodafone.model.User;
import dev.vodafone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return service.addUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return service.findAllUsers();
    }


    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
        return service.getUserByUserId(userId);
    }

    @GetMapping("/name/{name}")
    public List<User> findUserByName(@PathVariable String name){
        return service.getUserByName(name);
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
        return service.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return service.deleteUser(userId);
    }
}
