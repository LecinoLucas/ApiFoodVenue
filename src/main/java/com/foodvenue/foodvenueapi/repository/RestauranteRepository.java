package com.foodvenue.foodvenueapi.repository;

import com.foodvenue.foodvenueapi.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Restaurante findByUsuario_Email(String email);
    @Query("SELECT r FROM Restaurante r WHERE r.aberto = true")
    List<Restaurante> findAllOpenRestaurants();
}
