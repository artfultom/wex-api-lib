package com.github.artfultom.wexapi.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

@JsonDeserialize(using = TransactionType.Deserializer.class)
public enum TransactionType {

    DEPOSIT(1),
    WITHDRAWAL(2),
    CREDIT(4),
    DEBIT(5);

    private int code;

    TransactionType(int code) {
        this.code = code;
    }

    public static class Deserializer extends StdDeserializer<TransactionType> {

        public Deserializer() {
            super(TransactionType.class);
        }

        @Override
        public TransactionType deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
            int value = jp.getValueAsInt();

            for (TransactionType type : TransactionType.values()) {
                if (type.code == value) {
                    return type;
                }
            }

            return null;
        }
    }
}
