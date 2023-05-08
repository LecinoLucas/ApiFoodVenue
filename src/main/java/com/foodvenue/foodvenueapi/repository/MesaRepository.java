package com.foodvenue.foodvenueapi.repository;

import com.foodvenue.foodvenueapi.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
