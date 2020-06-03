package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.OrderDto;
import com.sda.werehouse.unit303.model.entity.OrderEnt;
import com.sda.werehouse.unit303.service.ItemService;
import com.sda.werehouse.unit303.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    @Autowired
    public OrderService orderService;
    @Autowired
    public ItemService itemService;
    public OrderDto orderDto;

    @GetMapping("/myOrder")
    public String getOrderByUser(Model model) {
        orderDto = new OrderDto();
        orderDto.setUserId(1L);
        orderDto.setOrderList(orderService.orderByUser(1L));
        model.addAttribute("orderforme", orderDto);
        model.addAttribute("itemList", itemService.getAllItems());
        model.addAttribute("postorder", new OrderEnt());
        return "/myOrder";
    }

    @PostMapping("/myOrder")
    public String postOrder(OrderEnt orderEnt) {
        orderEnt.getItemId();
        return "redirect:/myOrder";
    }
}
