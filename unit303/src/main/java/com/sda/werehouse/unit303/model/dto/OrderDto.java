package com.sda.werehouse.unit303.model.dto;

import com.sda.werehouse.unit303.model.entity.OrderEnt;

import java.util.Map;

public class OrderDto {

    private Long userId;
    private Map<Long, OrderEnt> orderList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, OrderEnt> getOrderList() {
        return orderList;
    }

    public void setOrderList(Map<Long, OrderEnt> orderList) {
        this.orderList = orderList;
    }
}
