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

    public void deleteMessagesFor(Long userId) {
        List<MessageDto> messageDtos = messageRepo.findAll().stream()
                .filter(messageDto -> messageDto.reciver.equals(userId)).collect(Collectors.toList());
        messageDtos.forEach(messageDto -> messageRepo.delete(messageDto));
    }

    public List<MessageDto> seeMessagesForMe(boolean seeAll) {
        String me = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<UserDto> optionalUserDto = userService.getAllUsers().values().stream()
                .filter(userDto1 -> userDto1.name == me).findAny();
        if(optionalUserDto.isPresent()) {
            authId = optionalUserDto.get().id;
        } else {
            authId = 0L;
        }
        List<MessageDto> messageDtoList;
        if(!seeAll) {
            messageDtoList = messageRepo.findAll().stream()
                    .filter(messageDto -> messageDto.reciver == authId).filter(messageDto -> messageDto.isRead() == seeAll)
                    .collect(Collectors.toList());
        } else {
            messageDtoList = messageRepo.findAll();
        }
        return messageDtoList;
    }

    public void markAsRead(MessageDto messageDto) {
        MessageDto messageDto1 = messageRepo.getOne(messageDto.getId());
        messageDto1.setRead(true);
        messageRepo.save(messageDto1);
    }
}
