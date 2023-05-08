package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Restaurante;
import com.foodvenue.foodvenueapi.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> findAll() {
        List<Restaurante> restaurantes = restauranteService.findAll();
        return new ResponseEntity<>(restaurantes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteService.findById(id);
        return restaurante.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Restaurante> save(@RequestBody Restaurante restaurante) {
        Restaurante newRestaurante = restauranteService.save(restaurante);
        return new ResponseEntity<>(newRestaurante, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Optional<Restaurante> oldRestaurante = restauranteService.findById(id);
        if (oldRestaurante.isPresent()) {
            Restaurante updatedRestaurante = oldRestaurante.get();
            updatedRestaurante.setNome(restaurante.getNome());
            updatedRestaurante.setDescricao(restaurante.getDescricao());
            updatedRestaurante.setImagem(restaurante.getImagem());
            updatedRestaurante.setAberto(restaurante.isAberto());
            restauranteService.save(updatedRestaurante);
            return new ResponseEntity<>(updatedRestaurante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteById (@PathVariable Long id){
            restauranteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}

