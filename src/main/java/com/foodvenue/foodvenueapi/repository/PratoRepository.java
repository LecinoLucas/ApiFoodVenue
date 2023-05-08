package com.foodvenue.foodvenueapi.repository;


import com.foodvenue.foodvenueapi.model.Prato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PratoRepository extends JpaRepository<Prato, Long> {
    List<Prato> findByRestauranteId(Long restauranteId);
}
