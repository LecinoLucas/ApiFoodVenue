package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Prato;
import com.foodvenue.foodvenueapi.repository.PratoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PratoService implements IPratoService{

    @Autowired
    private PratoRepository pratoRepository;

    public List<Prato> findAll() {
        return pratoRepository.findAll();
    }

    public Prato findById(Long id) {
        return pratoRepository.findById(id).orElseThrow(() -> new Error("Prato não encontrado"));
    }

    public List<Prato> findByRestauranteId(Long restauranteId) {
        return pratoRepository.findByRestauranteId(restauranteId);
    }

    public Prato save(Prato prato) {
        return pratoRepository.save(prato);
    }

    public Prato update(Long id, Prato prato) {
        Prato existingPrato = findById(id);
        BeanUtils.copyProperties(prato, existingPrato, "id");
        return pratoRepository.save(existingPrato);
    }

    public void delete(Long id) {
        Prato prato = findById(id);
        pratoRepository.delete(prato);
    }
}

