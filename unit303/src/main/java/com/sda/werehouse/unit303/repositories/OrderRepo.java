package com.sda.werehouse.unit303.repositories;

import com.sda.werehouse.unit303.model.entity.OrderEnt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderEnt, Long> {
}
