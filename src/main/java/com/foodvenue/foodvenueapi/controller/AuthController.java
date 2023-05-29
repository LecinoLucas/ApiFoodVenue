package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Usuario;
import com.foodvenue.foodvenueapi.payload.ApiResponse;
import com.foodvenue.foodvenueapi.payload.LoginRequest;
import com.foodvenue.foodvenueapi.payload.LoginResponse;
import com.foodvenue.foodvenueapi.payload.SignUpRequest;
import com.foodvenue.foodvenueapi.security.JwtTokenProvider;
import com.foodvenue.foodvenueapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // ...
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getSenha()
                )
        );
        Optional<Usuario> usuario = usuarioService.getByEmail(loginRequest.getEmail());
        if(!usuario.isPresent()){
                return new ResponseEntity<>(new ApiResponse(false, "Email ou senha invalidos!"), HttpStatus.BAD_REQUEST);
        }
        if(loginRequest.getClientType() == LoginRequest.ClientType.RESTAURANTE && usuario.get().getTipo() != Usuario.TipoUsuario.restaurante ){
            return new ResponseEntity<>(new ApiResponse(false, "Email ou senha invalidos!"), HttpStatus.BAD_REQUEST);
        }
        if(loginRequest.getClientType() == LoginRequest.ClientType.DELIVERY && usuario.get().getTipo() != Usuario.TipoUsuario.cliente ){
            return new ResponseEntity<>(new ApiResponse(false, "Email ou senha invalidos!"), HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication.getName());
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (usuarioService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email já cadastrado!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Criar uma nova instância de usuário e salvar no banco
        Usuario newUser = new Usuario();
        newUser.setNome(signUpRequest.getNome());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setSenha(passwordEncoder.encode(signUpRequest.getSenha())); // Criptografe a senha antes de salvar
        newUser.setTelefone(signUpRequest.getTelefone());
        newUser.setEndereco(signUpRequest.getEndereco());
        newUser.setTipo(signUpRequest.getTipo());

        Usuario result = usuarioService.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Usuário registrado com sucesso!"));
    }
}


