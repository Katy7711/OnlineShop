package com.example.onlinshop.service.impl;

import com.example.onlinshop.controller.UserController;
import com.example.onlinshop.dto.UserDto;
import com.example.onlinshop.entity.User;
import com.example.onlinshop.mapper.UserMapper;
import com.example.onlinshop.repository.UserRepository;
import com.example.onlinshop.service.UserService;
import jakarta.validation.ValidationException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;


@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  private final UserRepository userRepository;
  private final UserMapper userMapper;


  @Transactional(readOnly = true)
  @Override
  public User getUserById(long id) {
    log.info("Was invoked method for get user by id");
    return userRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Пользователь с id " + id + " не найден!"));
  }

  @Transactional(readOnly = true)
  @Override
  public List<UserDto> getUsers() {
    log.info("Was invoked method for get users");
    return userMapper.toDto(userRepository.findAll());
  }

  @Override
  public User createUser(UserDto user) {
    log.info("Was invoked method for create user");
    if (userRepository.existsByEmail(user.getEmail())) {
      log.warn("user already exists");
      throw new ValidationException(
          String.format("Пользователь \"%s\" уже существует!", user.getEmail()));
    }
    User createdUser = userMapper.userDtoToEntity(user);
    log.info("user created");
    return userRepository.save(createdUser);
  }

  @Override
  public User updateUser(String email, UserDto user) {
    log.info("Was invoked method for update user");
    User user1 = userRepository.findByEmail(email).orElseThrow();
    user1.getLOCK().lock();
    user1.setEmail(user.getEmail());
    user1.setName(user.getName());
    userRepository.save(user1);
    user1.getLOCK().unlock();
    return user1;
  }

  @Override
  public boolean deleteUser(long id) {
    log.info("Was invoked method for delete user with id {}", id);
    User user1 = userRepository.findById(id).orElseThrow();
    if (userRepository.existsByEmail(user1.getEmail())) {
      userRepository.delete(user1);
      log.info("user deleted");
      return true;
    }
    return false;
  }
}
