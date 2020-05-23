package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.Roles;
import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.model.entity.User;
import com.sda.werehouse.unit303.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private ModelMapper mapper;
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(ModelMapper mapper, UserRepo userRepo) {
        this.mapper = mapper;
        this.userRepo = userRepo;
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> userDtoList = userRepo.findAll().stream()
                .map(u -> mapper.map(u, UserDto.class)).collect(Collectors.toList());

        return userDtoList;
    }

    public void addUserToRepo(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepo.save(user);
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getName() + authentication.getAuthorities());
    }

    public void deleteUserFromRepo(UserDto userDto) {
        userRepo.deleteById(userDto.id);
    }
}
