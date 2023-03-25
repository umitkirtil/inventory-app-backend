package com.inventory.service;

import com.inventory.entity.History;
import com.inventory.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {


    @Autowired
    HistoryRepository historyRepository;


    public History saveHistory(History history){
        return historyRepository.save(history);
    }

}
