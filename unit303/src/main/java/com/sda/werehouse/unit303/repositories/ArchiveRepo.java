package com.sda.werehouse.unit303.repositories;

import com.sda.werehouse.unit303.model.entity.OrderArchiveEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveRepo extends JpaRepository<OrderArchiveEntry, Long> {
}
