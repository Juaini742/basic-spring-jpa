package com.core.jpatraining2.service;

import com.core.jpatraining2.entity.Users;
import com.core.jpatraining2.repository.UserRepository;
import com.core.jpatraining2.validation.UserDto;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(Users userRequest) throws IllegalAccessException {

        if (userRequest.getUsername() == null || userRequest.getUsername().trim().isEmpty()) {
            throw new IllegalAccessException("username is required");
        }

        if (userRequest.getPassword().trim().isEmpty() || userRequest.getPassword().length() < 6) {
            throw new IllegalAccessException("Password must be at least 6 characters");
        }

        List<Users> existUser = userRepository.findUniqueUsername(userRequest.getUsername());

        if (!existUser.isEmpty()) {
            throw new IllegalAccessException("Username already exists");
        }

        String hashPassword = BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt());
        userRequest.setPassword(hashPassword);

        userRepository.save(userRequest);
    }

    public void register(UserDto userDto, String hashPassword) {
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setPassword(hashPassword);
        userRepository.save(user);
    }

    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    public Users findById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users getUserByUsername(String username) {
        List<Users> users = userRepository.findUniqueUsername(username);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }
}
