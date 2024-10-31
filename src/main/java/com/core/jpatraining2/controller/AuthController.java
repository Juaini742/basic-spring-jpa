package com.core.jpatraining2.controller;

import com.core.jpatraining2.entity.Users;
import com.core.jpatraining2.exception.DuplicateAttributeException;
import com.core.jpatraining2.exception.GlobalExceptionHandler;
import com.core.jpatraining2.exception.NotFoundException;
import com.core.jpatraining2.service.UserService;
import com.core.jpatraining2.utils.JsonUtils;
import com.core.jpatraining2.validation.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.Cookie;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/public/auth") // jadi path untuk semua request harus didahului dengan /user
@Validated
public class AuthController {
    @Autowired
    UserService userService;

    @Autowired
    GlobalExceptionHandler globalExceptionHandler;

    @PostMapping("/register")
    public void createUser(HttpServletRequest req, HttpServletResponse resp) throws IllegalAccessException {
        try {
            Users user = JsonUtils.getObjectMapper().readValue(req.getReader(), Users.class);
            userService.createUser(user);
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().println("Registration successfully");
        } catch (IOException e) {
            globalExceptionHandler.handleServerError(e);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/register-2")
    public ResponseEntity<String> register2(@Valid @RequestBody UserDto userDto, HttpServletResponse resp) {
        Users existingUser = userService.getUserByUsername(userDto.getUsername());
        if (existingUser != null) {
            throw new DuplicateAttributeException("Username");
        }

        String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());
        userService.register(userDto, hashedPassword);

        UUID uuid = UUID.randomUUID();
        jakarta.servlet.http.Cookie cookie = new Cookie("token", uuid.toString());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge((int) Duration.ofDays(7).toSeconds());
        resp.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.CREATED).body("Registration successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserDto userDto, HttpServletResponse resp) {
        Users user = userService.getUserByUsername(userDto.getUsername());
        if (user == null) {
            throw new NotFoundException("User not found");
        }

        boolean hashedPassword = BCrypt.checkpw(userDto.getPassword(), user.getPassword());
        if (hashedPassword) {
            UUID uuid = UUID.randomUUID();
            jakarta.servlet.http.Cookie cookie = new Cookie("token", uuid.toString());
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setMaxAge((int) Duration.ofDays(7).toSeconds());
            resp.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Something went wrong");
    }


}
