package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface IReservaRepository {
    public List<Reserva> findAll();
    public Optional<Reserva> findById(Long id);
    public Reserva save(Reserva reserva);
    public void deleteById(Long id);
}
