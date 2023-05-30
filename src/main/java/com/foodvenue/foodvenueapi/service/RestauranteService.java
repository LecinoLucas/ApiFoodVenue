package com.foodvenue.foodvenueapi.service;

import com.foodvenue.foodvenueapi.model.ItemPedido;
import com.foodvenue.foodvenueapi.model.Restaurante;
import com.foodvenue.foodvenueapi.payload.ClientesDTO;
import com.foodvenue.foodvenueapi.repository.ItemPedidoRepository;
import com.foodvenue.foodvenueapi.repository.PedidoRepository;
import com.foodvenue.foodvenueapi.repository.PratoRepository;
import com.foodvenue.foodvenueapi.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestauranteService implements IRestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PratoRepository pratoRepository;
    @Override
    public List<Restaurante> findAll() {
        return restauranteRepository.findAll();
    }



    @Override
    public Optional<Restaurante> findById(Long id) {
        return restauranteRepository.findById(id);
    }

    @Override
    public Restaurante save(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public Restaurante findByUserEmail(String email) {
        return restauranteRepository.findByUsuario_Email(email);
    }

    @Override
    public void deleteById(Long id) {
        restauranteRepository.deleteById(id);
    }
    public List<ItemPedido> obterItensPedidoPorRestaurante(Long restauranteId) {
        return itemPedidoRepository.findByPedido_RestauranteId(restauranteId);
    }

    public List<ClientesDTO> obterTodosClientesPorRestaurante(Long restauranteId) {
        return pedidoRepository.findClientesByRestauranteId(restauranteId);
    }

    public BigDecimal calcularTicketMedio(Long restauranteId) {
        return pedidoRepository.calcularTicketMedio(restauranteId);
    }

    public BigDecimal calcularVendimentoDiario(Long restauranteId) {
        return pedidoRepository.calcularFaturamentoDiario(restauranteId);
    }
    public List<Restaurante> findAllOpenRestaurants() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return restaurantes.stream()
                .filter(Restaurante::isAberto)
                .collect(Collectors.toList());
    }
}