package com.sda.werehouse.unit303.model.dto;

import java.util.Map;

public class OrderDto {

    private Long userId;
    private Map<Long, Integer> orderList;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Map<Long, Integer> getOrderList() {
        return orderList;
    }

    public void setOrderList(Map<Long, Integer> orderList) {
        this.orderList = orderList;
    }
}
