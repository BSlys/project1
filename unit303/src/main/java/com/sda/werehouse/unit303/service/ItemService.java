package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.entity.Item;
import com.sda.werehouse.unit303.repositories.ItemRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private ModelMapper mapper;
    private ItemRepo itemRepo;
    private String message = "oczekiwanie na instrukcje";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ItemService(ModelMapper mapper, ItemRepo itemRepo) {
        this.mapper = mapper;
        this.itemRepo = itemRepo;
    }

    public void addItemToRepo(ItemDto itemDto) {

        itemRepo.save(mapper.map(itemDto, Item.class));
        message = "Dodano pozycję do spisu";
    }

    public Map<Long, ItemDto> getAllItems() {
        List<ItemDto> items = itemRepo.findAll().stream()
                .map(item -> mapper.map(item, ItemDto.class)).collect(Collectors.toList());
        Map<Long, ItemDto> itemMap = new HashMap<>();
        items.forEach(itemDto -> itemMap.put(itemDto.id, itemDto));
        return itemMap;
    }

    public boolean deleteItemFromRepo(ItemDto itemDto) {
        if(itemDto.itemRole.name().contains("SECURE")) {
            System.out.println("Próba usunięcia zabezpieczonego sprzętu");
            message = "Próba usunięcia zabezpieczonego sprzętu";
        } else {
            itemRepo.deleteById(itemDto.getId());
            message = "usunięto";
            return true;
        }
        return false;
    }
}
