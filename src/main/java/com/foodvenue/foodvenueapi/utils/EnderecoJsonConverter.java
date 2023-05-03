package com.foodvenue.foodvenueapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodvenue.foodvenueapi.model.Endereco;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EnderecoJsonConverter implements AttributeConverter<Endereco, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Endereco endereco) {
        try {
            return objectMapper.writeValueAsString(endereco);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter objeto Endereco para JSON", e);
        }
    }

    @Override
    public Endereco convertToEntityAttribute(String enderecoJson) {
        try {
            return objectMapper.readValue(enderecoJson, Endereco.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON para objeto Endereco", e);
        }
    }
}
