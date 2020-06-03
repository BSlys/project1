package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.configuration.AuthenticationMenagement;
import com.sda.werehouse.unit303.model.dto.OrderDto;
import com.sda.werehouse.unit303.model.entity.OrderEnt;
import com.sda.werehouse.unit303.service.ItemService;
import com.sda.werehouse.unit303.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class OrderController {
    @Autowired
    public OrderService orderService;
    @Autowired
    public AuthenticationMenagement authenticationMenagement;
    @Autowired
    public ItemService itemService;
    public OrderDto orderDto;

    @GetMapping("/myOrder")
    public String getOrderByUser(Model model) {
        orderDto = new OrderDto();
        orderDto.setUserId(authenticationMenagement.getAuthority().getMyId());
        orderDto.setOrderList(orderService.orderByUser(orderDto.getUserId()));
        model.addAttribute("orderforme", orderDto);
        model.addAttribute("itemList", itemService.getAllItems());
        model.addAttribute("postorder", new OrderEnt());
        return "/myOrder";
    }

    @PostMapping("/myOrder")
    public String postOrder(OrderEnt orderEnt) {
        Optional<OrderEnt> orderEntOptional = orderService.orderRepo.findAll()
                .stream().filter(orderEnt1 -> orderEnt1.getUserId().equals(orderEnt.getUserId()))
                .filter(orderEnt1 -> orderEnt1.getItemId().equals(orderEnt.getItemId())).findAny();
        if (orderEntOptional.isPresent()) {
            orderEnt.setId(orderEntOptional.get().getId());
        }
        orderService.orderRepo.save(orderEnt);
        return "redirect:/myOrder";
    }
}
