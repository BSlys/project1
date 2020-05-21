package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.model.entity.User;
import com.sda.werehouse.unit303.repositories.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private ModelMapper mapper;
    private UserRepo userRepo;

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
        userRepo.save(mapper.map(userDto, User.class));
    }

    public void deleteUserFromRepo(UserDto userDto) {
        userRepo.deleteById(userDto.id);
    }
}
