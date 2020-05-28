package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.dto.MessageDto;
import com.sda.werehouse.unit303.model.dto.UserDto;
import com.sda.werehouse.unit303.model.entity.User;
import com.sda.werehouse.unit303.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    public UserService userService;

    private MessageRepo messageRepo;
    private Long authId;

    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public void addMessage(MessageDto messageDto) {
        messageRepo.save(messageDto);
    }

    public List<MessageDto> seeMessagesForMe() {
        String me = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDto> optionalUserDto = userService.getAllUsers().stream().filter(userDto1 -> userDto1.name == me).findAny();
        if(optionalUserDto.isPresent()) {
            authId = optionalUserDto.get().id;
        } else {
            authId = 0L;
        }
        messageRepo.findAll();
        List<MessageDto> messageDtoList = messageRepo.findAll().stream()
                .filter(messageDto -> messageDto.reciver == authId).collect(Collectors.toList());
        return messageDtoList;
    }

    public void markAsRead(MessageDto messageDto) {

    }
}
