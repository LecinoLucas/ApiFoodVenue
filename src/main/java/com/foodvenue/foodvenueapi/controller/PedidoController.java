package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.Pedido;
import com.foodvenue.foodvenueapi.model.PedidoStatus;
import com.foodvenue.foodvenueapi.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        List<Pedido> pedidos = pedidoService.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Pedido>> getPedidosByClienteId(@PathVariable Long clienteId) {
        List<Pedido> pedidos = pedidoService.findByClienteId(clienteId);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/restaurante/{restauranteId}")
    public ResponseEntity<List<Pedido>> getPedidosByRestauranteId(@PathVariable Long restauranteId) {
        List<Pedido> pedidos = pedidoService.findByRestauranteId(restauranteId);
        return ResponseEntity.ok(pedidos);
    }


    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(@PathVariable Long id) {
        Pedido pedido = pedidoService.findById(id);
        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }
        if (pedido.getStatus() == PedidoStatus.ENTREGUE) {
            return ResponseEntity.badRequest().build();
        }
        pedido.setStatus(PedidoStatus.CANCELADO);
        Pedido updatedPedido = pedidoService.save(pedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody @Valid Pedido pedido) {
        Pedido createdPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        Pedido updatedPedido = pedidoService.update(id, pedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.ok().build();
    }
}