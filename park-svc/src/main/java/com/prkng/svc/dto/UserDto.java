package com.prkng.svc.dto;

import lombok.Data;

@Data
public class UserDto {

    private String username;
    private String password;
    private String role;
    private String status;
}
