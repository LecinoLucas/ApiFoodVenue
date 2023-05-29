package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Usuario;
import com.foodvenue.foodvenueapi.payload.CreateUserPayload;
import com.foodvenue.foodvenueapi.security.JwtTokenProvider;
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
    @GetMapping("/get")
    public ResponseEntity<Usuario> findUserByUserEmail(@RequestHeader("Authorization") String token) {
        token = token.substring(7);

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String email = jwtTokenProvider.getEmailFromJWT(token);

        Optional<Usuario> usuario = usuarioService.getByEmail(email);
        if (usuario.isPresent()) {
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<CreateUserPayload> save(@RequestBody Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
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
            newUser.setTelefone(usuario.getTelefone());
            newUser.setEndereco(usuario.getEndereco());
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
