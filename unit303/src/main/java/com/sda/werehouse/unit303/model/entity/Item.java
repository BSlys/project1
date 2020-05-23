package com.sda.werehouse.unit303.model.entity;

import com.sda.werehouse.unit303.model.ItemRole;

import javax.persistence.*;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ItemRole itemRole;

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
