package com.sda.werehouse.unit303.configuration;

import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuthenticationMenagement {
    Authentication authentication;
    private Long myId;
    @Autowired
    public UserService userService;

    public Authentication getAuthentication() {
        return authentication;
    }

    public Long getMyId() {
        return myId;
    }

    public AuthenticationMenagement getAuthority() {

        authentication = SecurityContextHolder.getContext().getAuthentication();
        String me = authentication.getName();
        Optional<UserDto> optionalUserDto = userService.getAllUsers().values()
                .stream().filter(userDto1 -> userDto1.name == me).findAny();
        if(optionalUserDto.isPresent()) {
            myId = optionalUserDto.get().id;
        } else {
            myId = 0L;
        }
        return this;
    }
}
