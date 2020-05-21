package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    public UserDto getAllUsers() {
        return new UserDto();
    }
}
