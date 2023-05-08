package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Prato;
import org.springframework.stereotype.Service;

import java.util.List;
public interface IPratoService {
    public List<Prato> findAll();
    public Prato findById(Long id);
    public List<Prato> findByRestauranteId(Long restauranteId);
    public Prato save(Prato prato);
    public Prato update(Long id, Prato prato);
    public void delete(Long id);
}

