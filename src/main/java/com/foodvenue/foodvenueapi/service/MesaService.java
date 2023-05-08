package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Mesa;
import com.foodvenue.foodvenueapi.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService implements IMesaService{

    @Autowired
    private MesaRepository mesaRepository;

    public Mesa createMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Optional<Mesa> findById(Long id) {
        return mesaRepository.findById(id);
    }

    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    public Mesa updateMesa(Long id, Mesa mesaDetails) {
        Mesa mesa = mesaRepository.findById(id).orElseThrow(); // Lançar exceção personalizada
        mesa.setNumero(mesaDetails.getNumero());
        mesa.setCapacidade(mesaDetails.getCapacidade());
        mesa.setDisponivel(mesaDetails.getDisponivel());

        return mesaRepository.save(mesa);
    }

    public void deleteMesa(Long id) {
        mesaRepository.deleteById(id);
    }
}
