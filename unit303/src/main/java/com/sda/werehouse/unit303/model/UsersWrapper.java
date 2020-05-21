package com.sda.werehouse.unit303.model;

import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersWrapper {
    public UserDto userDto = new UserDto();


    @Autowired
    public UserService userService;
}
