package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Prato;
import com.foodvenue.foodvenueapi.repository.PratoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PratoService implements IPratoService{

    @Autowired
    private PratoRepository pratoRepository;

    public List<Prato> findAll() {
        return pratoRepository.findAll().stream()
                .filter(prato -> !prato.isDeletado())
                .collect(Collectors.toList());
    }

    public Prato findById(Long id) {
        return pratoRepository.findByIdAndDeletadoFalse(id)
                .orElseThrow(() -> new RuntimeException("Prato não encontrado"));
    }
    public List<Prato> findByRestauranteId(Long restauranteId) {
        return pratoRepository.findByRestauranteIdAndDeletadoFalse(restauranteId);
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

