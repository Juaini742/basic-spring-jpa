package com.core.jpatraining2.controller;


import com.core.jpatraining2.entity.Users;
import com.core.jpatraining2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/secured/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get-all")
    public List<Users> getAllUsers() {
        return userService.getAllUser();
    }

    @GetMapping("/get-auth")
    public ResponseEntity<List<Users>> getAuthUsers() throws IOException {
        long id = 48;
        Users user = userService.findById(id);
        List<Users> userList = List.of(user);
        return ResponseEntity.ok(userList);
    }
}
