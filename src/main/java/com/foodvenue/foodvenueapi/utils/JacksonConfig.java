package com.foodvenue.foodvenueapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // Módulo para suportar tipos Java 8 date/time
        objectMapper.registerModule(new JavaTimeModule());

        // Seu módulo personalizado
        SimpleModule module = new SimpleModule();
        module.addSerializer(ByteArrayInputStream.class, new ByteArrayInputStreamSerializer());

        objectMapper.registerModule(module);

        return objectMapper;
    }
}

