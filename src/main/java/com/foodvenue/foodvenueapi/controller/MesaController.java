package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Mesa;
import com.foodvenue.foodvenueapi.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mesas")
public class MesaController {

    @Autowired
    private MesaService mesaService;

    @PostMapping
    public ResponseEntity<Mesa> createMesa(@RequestBody Mesa mesa) {
        return ResponseEntity.ok(mesaService.createMesa(mesa));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> getMesaById(@PathVariable Long id) {
        Optional<Mesa> mesa = mesaService.findById(id);
        return mesa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Mesa>> getAllMesas() {
        return ResponseEntity.ok(mesaService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> updateMesa(@PathVariable Long id, @RequestBody Mesa mesaDetails) {
        Mesa updatedMesa = mesaService.updateMesa(id, mesaDetails);
        return ResponseEntity.ok(updatedMesa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMesa(@PathVariable Long id) {
        mesaService.deleteMesa(id);
        return ResponseEntity.ok().build();
    }
}
