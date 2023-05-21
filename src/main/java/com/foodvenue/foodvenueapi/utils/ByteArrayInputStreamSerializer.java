package com.foodvenue.foodvenueapi.utils;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ByteArrayInputStreamSerializer extends JsonSerializer<ByteArrayInputStream> {

    @Override
    public void serialize(ByteArrayInputStream value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Converte a ByteArrayInputStream para um array de bytes
        byte[] bytes = value.readAllBytes();

        // Serializa o array de bytes
        gen.writeBinary(bytes);
    }
}
