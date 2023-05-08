package com.foodvenue.foodvenueapi.repository;

import com.foodvenue.foodvenueapi.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
