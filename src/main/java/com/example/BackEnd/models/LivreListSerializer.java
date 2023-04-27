package com.example.BackEnd.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class LivreListSerializer extends JsonSerializer<List<Livre>> {
    @Override
    public void serialize(List<Livre> livres, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Livre livre : livres) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", livre.getId());
            jsonGenerator.writeStringField("nom", livre.getNom());
            jsonGenerator.writeStringField("langues", livre.getLangues());
            jsonGenerator.writeNumberField("chapitresLus", livre.getChapitresLus());
            jsonGenerator.writeNumberField("notes", livre.getNotes());
            jsonGenerator.writeStringField("lien", livre.getLien());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
