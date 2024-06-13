package com.example.onlinshop.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.onlinshop.dto.UserDto;
import com.example.onlinshop.entity.User;
import com.example.onlinshop.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(UserController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

  private final MockMvc mockMvc;

  private final ObjectMapper objectMapper;

  @MockBean
  private UserServiceImpl userService;


  @Autowired
  UserControllerTest(MockMvc mockMvc, ObjectMapper objectMapper) {
    this.mockMvc = mockMvc;
    this.objectMapper = objectMapper;
  }

  @Test
  void addUser() throws Exception {
    User user = new User();

    user.setEmail("a@mail.ru");
    user.setName("Ivan");

    when(userService.createUser(any())).thenReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.post("/add/users").
            contentType(MediaType.APPLICATION_JSON).
            content(objectMapper.writeValueAsString(user))
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value(user.getEmail()))
        .andExpect(jsonPath("$.name").value(user.getName()));
  }

  @Test
  void getUsers() throws Exception {
    UserDto userDto = new UserDto();
    userDto.setEmail("a@mail.ru");
    userDto.setName("Ivan");
    UserDto userDto1 = new UserDto();
    userDto.setEmail("a@mail.ru");
    userDto.setName("Ivan");
    UserDto userDto2 = new UserDto();
    userDto.setEmail("a@mail.ru");
    userDto.setName("Ivan");
    List<UserDto> list = List.of(userDto, userDto1, userDto2);
    when(userService.getUsers()).thenReturn(list);

    mockMvc.perform(MockMvcRequestBuilders.get("/users"))
        .andDo(print())
        .andExpect(status().isOk());
    verify(userService, times(1)).getUsers();
  }

  @Test
  void update() throws Exception {
    User user = new User();

    user.setEmail("a@mail.ru");
    user.setName("Ivan");

    when(userService.updateUser(any(), any())).thenReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.patch("/update/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user))
            .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value(user.getEmail()))
        .andExpect(jsonPath("$.name").value(user.getName()));
  }

  @Test
  void getUser() throws Exception {
    User user = new User();

    user.setId(1L);
    user.setEmail("a@mail.ru");
    user.setName("Ivan");

    when(userService.getUserById(anyLong())).thenReturn(user);

    mockMvc.perform(MockMvcRequestBuilders.get("/user/1"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value(user.getEmail()))
        .andExpect(jsonPath("$.name").value(user.getName()));
  }
}
