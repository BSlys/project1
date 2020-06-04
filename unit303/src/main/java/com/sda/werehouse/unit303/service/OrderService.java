package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.entity.OrderEnt;
import com.sda.werehouse.unit303.repositories.OrderRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    public OrderRepo orderRepo;

    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public Map<Long, Integer> orderByUser(Long userId, boolean isReady) {
        List<OrderEnt> orderEnts = orderRepo.findAll().stream()
                .filter(orderEnt1 -> orderEnt1.getUserId() == userId)
                .filter(orderEnt -> orderEnt.isAccepted() == isReady)
                .collect(Collectors.toList());
        Map<Long, Integer> orderMap = new HashMap<>();
        orderEnts.stream().forEach(orderEnt -> orderMap.put(orderEnt.getItemId(), orderEnt.getQuantity()));
        return orderMap;
    }

    public void addBasicOrder(Long userId, Long itemId, int amount) {
        OrderEnt orderEnt = new OrderEnt();
        orderEnt.setUserId(userId);
        orderEnt.setItemId(itemId);
        orderEnt.setQuantity(amount);
        orderRepo.save(orderEnt);
    }

    public void deleteOrderForItem(ItemDto itemDto) {
        List<OrderEnt> optionalOrderEnt = orderRepo.findAll().stream()
                .filter(orderEnt -> orderEnt.getItemId().equals(itemDto.id)).collect(Collectors.toList());
        optionalOrderEnt.stream().forEach(orderEnt -> orderRepo.delete(orderEnt));
    }
}
