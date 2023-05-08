package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Mesa;

import java.util.List;
import java.util.Optional;

public interface IMesaService {
        public Mesa createMesa(Mesa mesa);
        public Optional<Mesa> findById(Long id);
        public List<Mesa> findAll();
        public Mesa updateMesa(Long id, Mesa mesaDetails);
        public void deleteMesa(Long id);
}


