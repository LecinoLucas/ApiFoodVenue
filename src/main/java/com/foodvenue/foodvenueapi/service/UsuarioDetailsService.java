package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.Usuario;
import com.foodvenue.foodvenueapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);

        if (!optionalUsuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado com email: " + email);
        }

        Usuario usuario = optionalUsuario.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (usuario.getTipo() == Usuario.TipoUsuario.cliente) {
            authorities.add(new SimpleGrantedAuthority("CLIENTE"));
        } else if (usuario.getTipo() == Usuario.TipoUsuario.restaurante) {
            authorities.add(new SimpleGrantedAuthority("RESTAURANTE"));
        }

        return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getSenha(), authorities);
    }
}
