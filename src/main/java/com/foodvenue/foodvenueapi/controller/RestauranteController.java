package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Restaurante;
import com.foodvenue.foodvenueapi.payload.RestauranteDTO;
import com.foodvenue.foodvenueapi.security.JwtTokenProvider;
import com.foodvenue.foodvenueapi.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> findAll() {
        List<Restaurante> restaurantes = restauranteService.findAll();
        return new ResponseEntity<>(restaurantes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> findById(@PathVariable Long id) {
        Optional<Restaurante> restaurante = restauranteService.findById(id);
        return restaurante.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Restaurante> save(@RequestBody RestauranteDTO restauranteDTO) {
        Restaurante restaurante = convertRestauranteDTOtoEntity(restauranteDTO);
        Restaurante newRestaurante = restauranteService.save(restaurante);
        return new ResponseEntity<>(newRestaurante, HttpStatus.CREATED);
    }

    @PutMapping("/status")
    public ResponseEntity<Restaurante> updateStatus(@RequestBody Map<String, Boolean> body,
                                                    @RequestHeader("Authorization") String token) {
        Boolean newStatus = body.get("aberto");
        token = token.substring(7);

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        if (newStatus == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String email = jwtTokenProvider.getEmailFromJWT(token);
        Restaurante oldRestaurante = restauranteService.findByUserEmail(email);
        if (oldRestaurante != null) {
            Restaurante updatedRestaurante = oldRestaurante;
            updatedRestaurante.setAberto(newStatus);
            restauranteService.save(updatedRestaurante);
            return new ResponseEntity<>(updatedRestaurante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Restaurante> getRestauranteByUserEmail(@RequestHeader("Authorization") String token) {
        token = token.substring(7);

        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        String email = jwtTokenProvider.getEmailFromJWT(token);
        Restaurante restaurante = restauranteService.findByUserEmail(email);

        if (restaurante != null) {
            return new ResponseEntity<>(restaurante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> update(@PathVariable Long id, @RequestBody RestauranteDTO restauranteDTO) {
        Optional<Restaurante> oldRestaurante = restauranteService.findById(id);
        if (oldRestaurante.isPresent()) {
            Restaurante updatedRestaurante = oldRestaurante.get();
            updatedRestaurante.setNome(restauranteDTO.getNome());
            updatedRestaurante.setDescricao(restauranteDTO.getDescricao());
            updatedRestaurante.setAberto(restauranteDTO.isAberto());

            // Remover a parte da URI que especifica o tipo de mídia
            String imagemBase64 = restauranteDTO.getImagem();
            int index = imagemBase64.indexOf(",");
            if (index != -1) {
                imagemBase64 = imagemBase64.substring(index + 1);
            }

            // Converter a imagem base64 para um array de bytes
            byte[] imagemBytes = Base64.getDecoder().decode(imagemBase64);
            updatedRestaurante.setImagemBytes(imagemBytes);

            restauranteService.save(updatedRestaurante);
            return new ResponseEntity<>(updatedRestaurante, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        restauranteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Método auxiliar para converter RestauranteDTO em Restaurante
    private Restaurante convertRestauranteDTOtoEntity(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteDTO.getNome());
        restaurante.setDescricao(restauranteDTO.getDescricao());
        restaurante.setAberto(restauranteDTO.isAberto());

        // Remover a parte da URI que especifica o tipo de mídia
        String imagemBase64 = restauranteDTO.getImagem();
        int index = imagemBase64.indexOf(",");
        if (index != -1) {
            imagemBase64 = imagemBase64.substring(index + 1);
        }

        // Converter a imagem base64 para um array de bytes
        byte[] imagemBytes = Base64.getDecoder().decode(imagemBase64);
        restaurante.setImagemBytes(imagemBytes);

        return restaurante;
    }
}