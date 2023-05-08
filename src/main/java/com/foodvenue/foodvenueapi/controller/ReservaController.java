package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Reserva;
import com.foodvenue.foodvenueapi.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> findAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.findById(id);
        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Reserva create(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@RequestBody Reserva reserva, @PathVariable Long id) {
        if (!reservaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reserva.setId(id);
        return ResponseEntity.ok(reservaService.save(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!reservaService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

