package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Prato;
import com.foodvenue.foodvenueapi.payload.PratoDTO;
import com.foodvenue.foodvenueapi.service.PratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/api/pratos")
public class PratoController {

    @Autowired
    private PratoService pratoService;

    @GetMapping
    public ResponseEntity<List<Prato>> getAllPratos() {
        List<Prato> pratos = pratoService.findAll();
        return ResponseEntity.ok(pratos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> getPratoById(@PathVariable Long id) {
        Prato prato = pratoService.findById(id);
        return ResponseEntity.ok(prato);
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Prato>> getPratosByRestauranteId(@PathVariable Long restauranteId) {
        List<Prato> pratos = pratoService.findByRestauranteId(restauranteId);
        return ResponseEntity.ok(pratos);
    }

    @PostMapping
    public ResponseEntity<Prato> createPrato(@RequestBody @Valid PratoDTO pratoDTO) {
        try {
            Prato prato = new Prato();
            prato.setNome(pratoDTO.getNome());
            prato.setDescricao(pratoDTO.getDescricao());
            prato.setPreco(pratoDTO.getPreco());
            prato.setRestaurante(pratoDTO.getRestaurante());
            // Remover a parte da URI que especifica o tipo de mídia
            String imagemBase64 = pratoDTO.getImagem();
            int index = imagemBase64.indexOf(",");
            if (index != -1) {
                imagemBase64 = imagemBase64.substring(index + 1);
            }

            // Converter a imagem base64 para um array de bytes
            byte[] imagemBytes = Base64.getDecoder().decode(imagemBase64);
            prato.setImagemBytes(imagemBytes);
            Prato createdPrato = pratoService.save(prato);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPrato);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }





    @PutMapping("/{id}")
    public ResponseEntity<Prato> updatePrato(@PathVariable Long id, @RequestBody @Valid PratoDTO pratoDTO) {
        try {
            Prato prato = new Prato();
            prato.setId(id);
            prato.setNome(pratoDTO.getNome());
            prato.setDescricao(pratoDTO.getDescricao());
            prato.setPreco(pratoDTO.getPreco());
            prato.setRestaurante(pratoDTO.getRestaurante());

            String imagemBase64 = pratoDTO.getImagem();
            int index = imagemBase64.indexOf(",");
            if (index != -1) {
                imagemBase64 = imagemBase64.substring(index + 1);
            }
            byte[] imagemBytes = Base64.getDecoder().decode(imagemBase64);
            prato.setImagemBytes(imagemBytes);

            Prato updatedPrato = pratoService.update(id, prato);
            return ResponseEntity.ok(updatedPrato);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrato(@PathVariable Long id) {
        Prato prato = pratoService.findById(id);
        prato.setDeletado(true);
        pratoService.save(prato);
        return ResponseEntity.ok().build();
    }
}

