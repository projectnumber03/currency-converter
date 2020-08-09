package ru.shilov.cc.currencyconverter.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public final class ValueDeserializer extends JsonDeserializer<Double> {

    @Override
    public Double deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final String value = jsonParser.readValueAs(String.class);
        return Double.parseDouble(value.replace(",", "."));
    }

}
