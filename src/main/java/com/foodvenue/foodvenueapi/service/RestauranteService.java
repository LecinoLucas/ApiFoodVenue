package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Restaurante;
import com.foodvenue.foodvenueapi.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService implements IRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Override
    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }

    @Override
    public Optional<Restaurante> findById(Long id) {
        return restauranteRepository.findById(id);
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public Restaurante findByUserEmail(String email) {
        return restauranteRepository.findByUsuario_Email(email);
    }

    @Override
    public void deleteById(Long id) {
        restauranteRepository.deleteById(id);
    }
}