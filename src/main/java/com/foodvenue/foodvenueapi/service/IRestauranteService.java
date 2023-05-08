package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Restaurante;

import java.util.List;
import java.util.Optional;

public interface IRestauranteService {
    List<Restaurante> findAll();

    Optional<Restaurante> findById(Long id);

    Restaurante save(Restaurante restaurante);

    void deleteById(Long id);
}