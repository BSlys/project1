package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.configuration.AuthenticationMenagement;
import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.dto.OrderDto;
import com.sda.werehouse.unit303.model.entity.OrderEnt;
import com.sda.werehouse.unit303.service.ItemService;
import com.sda.werehouse.unit303.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public OrderDto orderDtoPending;
    public OrderDto orderDtoAccepted;

    @GetMapping("/myOrder")
    public String getOrderByUser(Model model) {
        orderDtoPending = new OrderDto();
        orderDtoAccepted = new OrderDto();
        orderDtoPending.setUserId(authenticationMenagement.getAuthority().getMyId());
        orderDtoAccepted.setUserId(authenticationMenagement.getAuthority().getMyId());
        orderDtoPending.setOrderList(orderService.orderByUser(orderDtoPending.getUserId(), false));
        orderDtoAccepted.setOrderList(orderService.orderByUser(orderDtoAccepted.getUserId(), true));
        model.addAttribute("orderforme", orderDtoPending);
        model.addAttribute("acceptedorders", orderDtoAccepted);
        model.addAttribute("itemList", itemService.getAllItems());
        model.addAttribute("postorder", new OrderEnt());
        return "/myOrder";
    }

    @PostMapping("/myOrder")
    public String postOrder(OrderEnt orderEnt) {
        /*
        Optional<OrderEnt> orderEntOptional = orderService.orderRepo.findAll()
                .stream().filter(orderEnt1 -> orderEnt1.getUserId().equals(orderEnt.getUserId()))
                .filter(orderEnt1 -> orderEnt1.getItemId().equals(orderEnt.getItemId()))
                .filter(orderEnt1 -> !orderEnt1.isAccepted()).findAny();
        if (orderEntOptional.isPresent()) {
            orderEnt.setId(orderEntOptional.get().getId());
        }
        */
        orderService.orderRepo.save(orderEnt);
        return "redirect:/myOrder";
    }

    @PostMapping("/deleteOrder")
    public String deleteOrder(OrderEnt orderEnt) {
        OrderEnt finalOrderEnt = orderEnt;
        Optional<OrderEnt> orderEntOptional = orderService.orderRepo.findAll()
                .stream().filter(orderEnt1 -> orderEnt1.getId().equals(finalOrderEnt.getId()))
                .findAny();
        if (orderEntOptional.isPresent()) {
            orderEnt.setId(orderEntOptional.get().getId());
            orderService.orderRepo.delete(orderEnt);
        }
        return "redirect:/myOrder";
    }



    @PostMapping("/returnOrder")
    public String returnOrder(OrderEnt orderEnt) {
        OrderEnt finalOrderEnt = orderEnt;
        Optional<OrderEnt> orderEntOptional = orderService.orderRepo.findAll()
                .stream().filter(orderEnt1 -> orderEnt1.getId().equals(finalOrderEnt.getId()))
                .findAny();
        if (orderEntOptional.isPresent()) {
            orderEnt = orderEntOptional.get();
            orderEnt.setAccepted(false);
            orderService.orderRepo.save(orderEnt);
        }
        return "redirect:/myOrder";
    }




}
