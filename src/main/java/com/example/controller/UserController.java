package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<List<User>> createUser(@RequestParam(name = "size", defaultValue = "1") int size) {
        List<User> savedUsers = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            // Implement user creation logic and call userService.saveUser()
            // For demonstration purposes, let's create a sample user
            User user = new User();
            user.setName("Sample User " + i);
            user.setAge(30);
            user.setGender("Male");
            user.setDob(LocalDate.of(1990, 1, 1));
            user.setNationality("US");
            user.setVerificationStatus("VERIFIED");
            savedUsers.add(userService.saveUser(user));
        }

        return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(
            @RequestParam(name = "sortType", defaultValue = "Name") String sortType,
            @RequestParam(name = "sortOrder", defaultValue = "ODD") String sortOrder,
            @RequestParam(name = "limit", defaultValue = "5") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset) {

        // Implement user retrieval logic and call userService.getAllUsers()
        List<User> users = userService.getAllUsers();

        // Apply sorting, ordering, limit, and offset as needed
        // For demonstration purposes, let's sort by user name
        users.sort((user1, user2) -> {
            if ("Age".equalsIgnoreCase(sortType)) {
                return Integer.compare(user1.getAge(), user2.getAge());
            } else {
                return user1.getName().compareToIgnoreCase(user2.getName());
            }
        });

        // Apply sortOrder logic (even/odd)
        if ("EVEN".equalsIgnoreCase(sortOrder)) {
            users.removeIf(user -> user.getAge() % 2 != 0);
        } else if ("ODD".equalsIgnoreCase(sortOrder)) {
            users.removeIf(user -> user.getAge() % 2 == 0);
        }

        // Apply limit and offset
        int endIndex = Math.min(offset + limit, users.size());
        List<User> paginatedUsers = users.subList(offset, endIndex);

        // Return the list of users along with pagination information
        return ResponseEntity.ok(paginatedUsers);
    }

    // Add more endpoints as needed
}
