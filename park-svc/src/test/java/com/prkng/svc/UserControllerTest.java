package com.prkng.svc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.prkng.svc.controller.UserController;
import com.prkng.svc.dto.UserDto;
import com.prkng.svc.model.Users;
import com.prkng.svc.service.UserService;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsername("entry_op1");
        userDto.setPassword("admin123!");
        userDto.setRole("Operator Entry");
        userDto.setStatus("aktif");

        Users users = new Users();
        users.setUserId(UUID.fromString("cc7e19ca-95cd-4fb3-8cbd-f9a838ec4dd4"));
        users.setUsername("entry_op1");
        users.setPassword("$2a$10$n.F6Z6t2zbgfws3U/bj6puljjmtQaHscxAEL5j1avh8DX169IzZBm");
        users.setRole("Operator Entry");
        users.setStatus("aktif");

        when(userService.addUser(any(UserDto.class))).thenReturn(users);

        mockMvc.perform(post("/users/addUser")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("entry_op1"))
            .andExpect(jsonPath("$.password").value("$2a$10$n.F6Z6t2zbgfws3U/bj6puljjmtQaHscxAEL5j1avh8DX169IzZBm"))
            .andExpect(jsonPath("$.userId").value("cc7e19ca-95cd-4fb3-8cbd-f9a838ec4dd4"))
            .andExpect(jsonPath("$.status").value("aktif"));

    }

    @Test
    void loginTest() throws Exception {

        UserDto userDto = new UserDto();
        userDto.setUsername("entry_op1");
        userDto.setPassword("admin123!");
        
        Users users = new Users();
        users.setUserId(UUID.fromString("cc7e19ca-95cd-4fb3-8cbd-f9a838ec4dd4"));
        users.setUsername("entry_op1");
        users.setPassword("$2a$10$n.F6Z6t2zbgfws3U/bj6puljjmtQaHscxAEL5j1avh8DX169IzZBm");
        users.setRole("Operator Entry");
        users.setStatus("aktif");

        when(userService.getUserByUsername(anyString(), anyString())).thenReturn(users);

        mockMvc.perform(post("/users/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.username").value("entry_op1"))
            .andExpect(jsonPath("$.password").value("$2a$10$n.F6Z6t2zbgfws3U/bj6puljjmtQaHscxAEL5j1avh8DX169IzZBm"))
            .andExpect(jsonPath("$.userId").value("cc7e19ca-95cd-4fb3-8cbd-f9a838ec4dd4"))
            .andExpect(jsonPath("$.status").value("aktif"));
    }
    
}
