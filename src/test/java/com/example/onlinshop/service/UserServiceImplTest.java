package com.example.onlinshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.example.onlinshop.dto.UserDto;
import com.example.onlinshop.entity.User;
import com.example.onlinshop.mapper.UserMapper;
import com.example.onlinshop.repository.UserRepository;
import com.example.onlinshop.service.impl.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.webjars.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  UserMapper userMapper;

  @InjectMocks
  private UserServiceImpl userService;

  @Test
  void createUser() {
    User user = new User();
    UserDto userDto = new UserDto();

    user.setEmail("a@mail.ru");
    user.setName("Ivan");

    userDto.setEmail("a@mail.ru");
    userDto.setName("Ivan");

    when(userRepository.existsByEmail(any())).thenReturn(false);
    when(userMapper.userDtoToEntity(any(UserDto.class))).thenReturn(user);
    when(userRepository.save(any(User.class))).thenReturn(user);

    User user2 = userService.createUser(new UserDto());

    assertEquals(user, user2);
    verify(userRepository, times(1)).save(any(User.class));
    verify(userRepository, times(1)).save(any(User.class));

  }

  @Test
  void getUsers() {
    List<User> users = new ArrayList<>();
    User user1 = new User();
    User user2 = new User();

    user1.setEmail("a@mail.ru");
    user1.setName("Ivan");

    user2.setEmail("b@mail.ru");
    user2.setName("Ivan2");

    users.add(user1);
    users.add(user2);

    List<UserDto> usersDto = new ArrayList<>();

    UserDto userDto1 = new UserDto();
    UserDto userDto2 = new UserDto();

    userDto1.setEmail("a@mail.ru");
    userDto1.setName("Ivan");

    userDto2.setEmail("b@mail.ru");
    userDto2.setName("Ivan2");

    usersDto.add(userDto1);
    usersDto.add(userDto2);

    when(userRepository.findAll()).thenReturn(users);
    when(userMapper.toDto(anyCollection())).thenReturn(usersDto);

    List<UserDto> usersDto2 = userService.getUsers();

    assertEquals(usersDto, usersDto2);
    assertEquals(2, usersDto2.size());
    verify(userRepository, times(1)).findAll();
  }

  @Test
  void update() {
    User user = new User();
    UserDto userDto = new UserDto();

    user.setEmail("a@mail.ru");
    user.setName("Ivan");

    userDto.setEmail("a@mail.ru");
    userDto.setName("Ivan");

    when(userRepository.findByEmail(any())).thenReturn(Optional.of(user));
    when(userRepository.save(any(User.class))).thenReturn(user);

    User user2 = userService.updateUser("a@mail.ru", new UserDto());

    assertEquals(user, user2);
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  void getUserById() {
    User user = new User();

    user.setId(1L);
    user.setEmail("a@mail.ru");
    user.setName("Ivan");

    when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

    User user2 = userService.getUserById(1L);

    assertEquals(user, user2);
    verify(userRepository, times(1)).findById(anyLong());
  }

  @Test
  void getUserByIdThrows() {
    when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(NotFoundException.class, () -> userService.getUserById(1L));
  }

}
