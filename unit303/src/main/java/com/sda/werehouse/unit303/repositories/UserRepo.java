package com.sda.werehouse.unit303.repositories;

import com.sda.werehouse.unit303.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
}
