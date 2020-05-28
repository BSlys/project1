package com.sda.werehouse.unit303.repositories;

import com.sda.werehouse.unit303.model.dto.MessageDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<MessageDto, Long> {
}
