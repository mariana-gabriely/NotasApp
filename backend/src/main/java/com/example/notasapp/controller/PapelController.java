package com.example.notasapp.controller;

import com.example.notasapp.model.entity.Papel;
import com.example.notasapp.model.service.PapelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/papel")
@CrossOrigin(origins = "*")
public class PapelController {

    @Autowired
    private PapelService papelService;

    @PostMapping("/novo")
    public ResponseEntity<Boolean> save(@RequestBody Papel papel) {
        try {
            papelService.save(papel);
            return ResponseEntity.status(201).body(true);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
