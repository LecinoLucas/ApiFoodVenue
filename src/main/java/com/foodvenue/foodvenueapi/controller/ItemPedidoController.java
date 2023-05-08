package com.foodvenue.foodvenueapi.controller;
import com.foodvenue.foodvenueapi.model.ItemPedido;
import com.foodvenue.foodvenueapi.service.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/itens_pedido")
public class ItemPedidoController {

    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping
    public List<ItemPedido> findAll() {
        return itemPedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemPedido> findById(@PathVariable Long id) {
        Optional<ItemPedido> itemPedido = itemPedidoService.findById(id);
        if (itemPedido.isPresent()) {
            return ResponseEntity.ok(itemPedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ItemPedido create(@RequestBody ItemPedido itemPedido) {
        return itemPedidoService.save(itemPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemPedido> update(@RequestBody ItemPedido itemPedido, @PathVariable Long id) {
        if (!itemPedidoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemPedido.setId(id);
        return ResponseEntity.ok(itemPedidoService.save(itemPedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!itemPedidoService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        itemPedidoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

