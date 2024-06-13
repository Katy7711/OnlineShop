package com.example.onlinshop.service;

import com.example.onlinshop.dto.UserDto;
import com.example.onlinshop.entity.User;
import java.util.List;

public interface UserService {

  User getUserById(long id);

  List<UserDto> getUsers();

  User createUser(UserDto user);

  User updateUser(String email, UserDto user);

  boolean deleteUser(long id);


}
