package com.example.onlinshop.controller;

import com.example.onlinshop.dto.ResponseWrapper;
import com.example.onlinshop.dto.UserDto;
import com.example.onlinshop.dto.View;
import com.example.onlinshop.entity.User;
import com.example.onlinshop.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  private final UserService userService;


  @GetMapping("user/{id}")
  @JsonView(View.UserDetails.class)
  public ResponseEntity<User> getUserById(@PathVariable long id) {
    log.info("Request for get user by id");
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @GetMapping("/users")
  public ResponseWrapper<UserDto> getUsers() {
    log.info("Request for get users");
    return ResponseWrapper.of(userService.getUsers());
  }

  @PostMapping("/add/users")
  @JsonView(View.UserSummary.class)
  public ResponseEntity<User> addUser(@RequestBody UserDto user) {
    log.info("Request for add user");
    return ResponseEntity.ok(userService.createUser(user));
  }

  @PatchMapping("/update/user")
  @JsonView(View.UserSummary.class)
  public ResponseEntity<User> updateUser(String email, @Valid @RequestBody UserDto user) {
    log.info("Request for update user");
    return ResponseEntity.ok(userService.updateUser(email, user));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> removeUser(@PathVariable long id) {
    log.info("Request for delete user by id");
    if (userService.deleteUser(id)) {
      return ResponseEntity.ok().build();
    }
    return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).build();
  }
}
