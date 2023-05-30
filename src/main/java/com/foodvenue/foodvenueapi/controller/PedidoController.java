package com.foodvenue.foodvenueapi.controller;

import com.foodvenue.foodvenueapi.model.*;
import com.foodvenue.foodvenueapi.payload.ItemPedidoDTO;
import com.foodvenue.foodvenueapi.payload.PedidoCreateDTO;
import com.foodvenue.foodvenueapi.service.PedidoService;
import com.foodvenue.foodvenueapi.service.PratoService;
import com.foodvenue.foodvenueapi.service.RestauranteService;
import com.foodvenue.foodvenueapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private PratoService pratoService;

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


    @GetMapping("/status/{id}")
    public ResponseEntity<Pedido> getPedidoByStatus(@PathVariable Long id) {
        Optional<Pedido> optionalPedido = pedidoService.findPedidosNotEntregueOrCancelado(id);
        if (optionalPedido.isPresent()) {
            return ResponseEntity.ok(optionalPedido.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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
        this.template.convertAndSend("/topic/pedidos/"+id,updatedPedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @PostMapping("/new")
    public ResponseEntity<Pedido> createPedido(@RequestBody @Valid PedidoCreateDTO pedidoCreateDTO) {
        Optional <Usuario> cliente = usuarioService.findById(pedidoCreateDTO.getClienteId());
        Optional <Restaurante> restaurante = restauranteService.findById(pedidoCreateDTO.getRestauranteId());
        Endereco enderecoEntrega = pedidoCreateDTO.getEnderecoEntrega();

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente.get());
        pedido.setRestaurante(restaurante.get());
        pedido.setEnderecoEntrega(enderecoEntrega);
        pedido.setStatus(PedidoStatus.AGUARDANDO_APROVACAO);
        pedido.setDataHora(LocalDateTime.now());

        List<ItemPedido> itens = new ArrayList<>();

        for (ItemPedidoDTO itemPedidoDTO : pedidoCreateDTO.getItens()) {
            Prato prato = pratoService.findById(itemPedidoDTO.getProdutoId());
            Integer quantidade = 1;
            String observacoes = "Em andamento";

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setPrato(prato);
            itemPedido.setQuantidade(quantidade);
            itemPedido.setObservacoes(observacoes);

            itens.add(itemPedido);
        }

        pedido.setItens(itens);

        Pedido createdPedido = pedidoService.save(pedido);
        this.template.convertAndSend("/topic/pedidos/new/" + restaurante.get().getId(), createdPedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedido);
    }
    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody @Valid Pedido pedido) {
        Pedido createdPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody @Valid Pedido pedido) {
        Pedido updatedPedido = pedidoService.update(id, pedido);
        this.template.convertAndSend("/topic/pedidos/" + id, updatedPedido);
        return ResponseEntity.ok(updatedPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.ok().build();
    }
}