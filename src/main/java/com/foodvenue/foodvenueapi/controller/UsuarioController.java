package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Usuario;
import com.foodvenue.foodvenueapi.payload.CreateUserPayload;
import com.foodvenue.foodvenueapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CreateUserPayload> save(@RequestBody Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha())); // Criptografe a senha antes de salvar
        Usuario newUser = usuarioService.save(usuario);
        CreateUserPayload userDto = new CreateUserPayload(newUser.getId(), newUser.getNome(), newUser.getEmail(),
                newUser.getTelefone(), newUser.getEndereco(), newUser.getTipo());
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> oldUser = usuarioService.findById(id);
        if (oldUser.isPresent()) {
            Usuario newUser = oldUser.get();
            newUser.setNome(usuario.getNome());
            newUser.setEmail(usuario.getEmail());
            newUser.setSenha(passwordEncoder.encode(usuario.getSenha())); // Criptografe a senha antes de salvar
            newUser.setTelefone(usuario.getTelefone());
            newUser.setEndereco(usuario.getEndereco());
            newUser.setTipo(usuario.getTipo());
            usuarioService.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
