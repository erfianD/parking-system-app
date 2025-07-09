package com.prkng.svc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prkng.svc.dto.UserDto;
import com.prkng.svc.model.Users;
import com.prkng.svc.repository.CustomRepository;
import com.prkng.svc.repository.UsersRepository;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private CustomRepository customRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    // public UserService(BCryptPasswordEncoder passwordEncoder) {
    //     this.passwordEncoder = passwordEncoder;
    // }
    
    public Users addUser(UserDto userDto) {

        Users userData = new Users();
        
        userData.setUsername(userDto.getUsername());
        String setPassword = regisUser(userDto.getUsername(), userDto.getPassword());
        userData.setPassword(setPassword);
        userData.setRole(userDto.getRole());
        usersRepository.save(userData);
        return userData;
    }

    public String regisUser(String username, String password) {
        String hashPasssword = passwordEncoder.encode(password);
        return hashPasssword;
    }

    public Users getUserByUsername(String username, String password) {
        Users user = customRepository.getUserByUsername(username);

        if (user != null) {
            if (checkPassword(password, user.getPassword()) == false) {
                return user = null;
            }
        }
        
        return user;
    }


    public boolean checkPassword(String password, String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
