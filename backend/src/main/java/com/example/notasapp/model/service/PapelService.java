package com.example.notasapp.model.service;

import com.example.notasapp.model.entity.Papel;
import com.example.notasapp.model.repositories.PapelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PapelService {

    @Autowired
    private PapelRepository papelRepository;

    public void save(Papel papel) {
        papelRepository.save(papel);
    }
}
