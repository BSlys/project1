package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.dto.ItemDto;
import com.sda.werehouse.unit303.model.dto.UserDto;

public interface OrderInterface {

    public OrderInterface getOrderByUser(UserDto userDto);

    public void deleteOrderByUser(UserDto userDto);

    public ItemDto itemFromOrder(OrderInterface orderInterface, Long itemID);
}
