package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Prato;
import com.foodvenue.foodvenueapi.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pratos")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @GetMapping
    public ResponseEntity<List<Prato>> getAllPratos() {
        List<Prato> pratos = pratoService.findAll();
        return ResponseEntity.ok(pratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> getPratoById(@PathVariable Long id) {
        Prato prato = pratoService.findById(id);
        return ResponseEntity.ok(prato);
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Prato>> getPratosByRestauranteId(@PathVariable Long restauranteId) {
        List<Prato> pratos = pratoService.findByRestauranteId(restauranteId);
        return ResponseEntity.ok(pratos);
    }

    @PostMapping
    public ResponseEntity<Prato> createPrato(@RequestBody @Valid Prato prato) {
        Prato createdPrato = pratoService.save(prato);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prato> updatePrato(@PathVariable Long id, @RequestBody @Valid Prato prato) {
        Prato updatedPrato = pratoService.update(id, prato);
        return ResponseEntity.ok(updatedPrato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable Long id) {
        pratoService.delete(id);
        return ResponseEntity.ok().build();
    }
}

