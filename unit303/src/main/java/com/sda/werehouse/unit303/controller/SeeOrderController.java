package com.sda.werehouse.unit303.controller;

import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.entity.OrderEnt;
import com.sda.werehouse.unit303.service.ArchiveService;
import com.sda.werehouse.unit303.service.ItemService;
import com.sda.werehouse.unit303.service.OrderService;
import com.sda.werehouse.unit303.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class SeeOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserService userService;
    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/seeOrders")
    public String seeOrdersFor(ItemDto itemDto, Model model) {
        Map<Long, OrderEnt> userOrderMaptrue = new HashMap<>();
        orderService.orderRepo.findAll().stream().filter(orderEnt -> orderEnt.getItemId().equals(itemDto.id))
                .filter(orderEnt -> orderEnt.isAccepted())
                .forEach(orderEnt -> userOrderMaptrue.put(orderEnt.getId(),orderEnt));
        Map<Long, OrderEnt> userOrderMapfalse = new HashMap<>();
        orderService.orderRepo.findAll().stream().filter(orderEnt -> orderEnt.getItemId().equals(itemDto.id))
                .filter(orderEnt -> !orderEnt.isAccepted())
                .forEach(orderEnt -> userOrderMapfalse.put(orderEnt.getId(),orderEnt));
        ItemDto item = itemService.getAllItems().values().stream()
                .filter(itemDto1 -> itemDto1.getId().equals(itemDto.getId())).findAny().get();
        Long rentAmount = orderService.getRentamount(itemDto.id);
        model.addAttribute("rented", rentAmount);
        model.addAttribute("ordersForThisT", userOrderMaptrue);
        model.addAttribute("ordersForThisF", userOrderMapfalse);
        model.addAttribute("name", item.getName());
        model.addAttribute("amount", item.getQuantity());
        model.addAttribute("userMap", userService.getAllUsers());
        return "/seeorders";
    }

    @PostMapping("/deleteOrderI")
    public String deleteOrderI(OrderEnt orderEnt) {
        Optional<OrderEnt> orderEntOptional = orderService.orderRepo.findAll()
                .stream().filter(orderEnt1 -> orderEnt1.getId().equals(orderEnt.getId()))
                .filter(orderEnt1 -> !orderEnt1.isAccepted()).findAny();
        if (orderEntOptional.isPresent()) {
            orderEnt.setId(orderEntOptional.get().getId());
            orderService.orderRepo.delete(orderEnt);
        } else {
            System.out.println("błąd usuwania zamówienia");
            return "redirect:/inventory";
        }
        return "redirect:/seeOrders?id=" + orderEnt.getItemId();
    }

    @PostMapping("/acceptOrder")
    public String acceptOrder(OrderEnt orderEnt) {
        Optional<OrderEnt> orderEntOptional = orderService.orderRepo.findAll()
                .stream().filter(orderEnt1 -> orderEnt1.getId().equals(orderEnt.getId()))
                .filter(orderEnt1 -> !orderEnt1.isAccepted()).findAny();
        if (orderEntOptional.isPresent()) {
            OrderEnt order1 = orderEntOptional.get();
            if (itemService.isEnought(order1.getItemId(),
                    (order1.getQuantity() + orderService.getRentamount(order1.getItemId())))) {
                orderEntOptional.get().setAccepted(true);
                orderService.orderRepo.save(orderEntOptional.get());
                archiveService.updateArchiveEntry(orderEntOptional.get());
            } else {
                String message = "Za malo przedmiotow na stanie";
                return "redirect:/fallback?message=" + message;
            }
        } else {
            System.out.println("błąd akcepacji zamówienia");
            return "redirect:/inventory";
        }
        return "redirect:/seeOrders?id=" + orderEntOptional.get().getItemId();
    }


}
