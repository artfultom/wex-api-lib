package com.github.artfultom.wexapi.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class BooleanDeserializer extends StdDeserializer<Boolean> {

    public BooleanDeserializer() {
        this(null);
    }

    public BooleanDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Boolean deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        return jsonparser.getIntValue() == 1;
    }
}