package com.User.Service.controller;


import com.User.Service.dto.UserRequestDto;
import com.User.Service.dto.UserResponseDto;
import com.User.Service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create user
    @PostMapping
    public ResponseEntity<UserResponseDto> postUserDetails(
            @RequestBody UserRequestDto userRequestDto) {

        UserResponseDto response = userService.postUserDetails(userRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUserDetails(
            UserRequestDto userRequestDto) {

        List<UserResponseDto> response =
                userService.getAllUserDetails(userRequestDto);

        return ResponseEntity.ok(response);
    }

    // Get user by ID
    @GetMapping("/id")
    public ResponseEntity<UserResponseDto> getUserById(
            UserRequestDto userRequestDto) {

        UserResponseDto response =
                userService.getUserById(userRequestDto);

        return ResponseEntity.ok(response);
    }

    // Update user by username
    @PutMapping("/{userName}")
    public ResponseEntity<UserResponseDto> updateUserByUsername(
            @PathVariable String userName) {

        UserResponseDto response =
                userService.updateUserByUsername(userName);

        return ResponseEntity.ok(response);
    }

    // Delete user by username
    @DeleteMapping("/{userName}")
    public ResponseEntity<UserResponseDto> deleteUser(
            @PathVariable String userName) {

        UserResponseDto response =
                userService.deleteUser(userName);

        return ResponseEntity.ok(response);
    }
}