package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
    boolean existsByEmail(String email);
}
