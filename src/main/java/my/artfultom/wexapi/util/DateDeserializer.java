package my.artfultom.wexapi.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateDeserializer extends StdDeserializer<LocalDateTime> {

    public DateDeserializer() {
        this(null);
    }

    public DateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
        Long dateTime = jsonparser.getLongValue();

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(dateTime * 1000), ZoneId.systemDefault()); // TODO zoneid
    }
}