package com.sda.werehouse.unit303.service;

import com.sda.werehouse.unit303.model.entity.OrderArchiveEntry;
import com.sda.werehouse.unit303.model.entity.OrderEnt;
import com.sda.werehouse.unit303.repositories.ArchiveRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArchiveService {

    public ArchiveRepo archiveRepo;
    private ModelMapper mapper;

    public ArchiveService(ArchiveRepo archiveRepo, ModelMapper mapper) {
        this.archiveRepo = archiveRepo;
        this.mapper = mapper;
    }

    public void updateArchiveEntry(OrderEnt orderEnt) {
        Optional<OrderArchiveEntry> optionalOrderArchiveEntry = archiveRepo.findById(orderEnt.getId());
        if (optionalOrderArchiveEntry.isPresent()) {
            OrderArchiveEntry orderArchiveEntry = optionalOrderArchiveEntry.get();
            if (orderEnt.isAccepted()) {
                orderArchiveEntry.setAcceptTime(LocalDateTime.now());
            } else {
                orderArchiveEntry.setReturnTime(LocalDateTime.now());
            }
            archiveRepo.save(orderArchiveEntry);
        } else {
            OrderArchiveEntry orderArchiveEntry = mapper.map(orderEnt, OrderArchiveEntry.class);
            orderArchiveEntry.setPostTime(LocalDateTime.now());
            archiveRepo.save(orderArchiveEntry);
        }

    }
}
