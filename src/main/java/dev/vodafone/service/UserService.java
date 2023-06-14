package dev.vodafone.service;

import dev.vodafone.model.User;
import dev.vodafone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        user.setUserId(UUID.randomUUID().toString().split("-")[0]);
        return userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserId(String userId){
        return userRepository.findById(userId).get();
    }

    public List<User> getUserByName(String name){
        return  userRepository.findByName(name);
    }

    public User updateUser(User userRequest) {
        Optional<User> optionalExistingUser = userRepository.findById(userRequest.getUserId());
        if (optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();
            existingUser.setName(userRequest.getName());
            existingUser.setSurname(userRequest.getSurname());
            existingUser.setEmail(userRequest.getEmail());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public String deleteUser(String userId){
        userRepository.deleteById(userId);
        return userId+" user deleted from database.";
    }
}
