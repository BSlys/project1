package com.sda.werehouse.unit303.model.dto;

import com.sda.werehouse.unit303.model.ItemRole;

public class ItemDto {

    public Long id;
    public String name;
    public ItemRole itemRole;
    public int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemRole getItemRole() {
        return itemRole;
    }

    public void setItemRole(ItemRole itemRole) {
        this.itemRole = itemRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
