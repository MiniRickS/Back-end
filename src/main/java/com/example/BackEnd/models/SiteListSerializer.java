package com.example.BackEnd.models;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

public class SiteListSerializer extends JsonSerializer<List<Site>> {
    @Override
    public void serialize(List<Site> sites, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Site site : sites) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", site.getId());
            jsonGenerator.writeStringField("nom", site.getNom());
            jsonGenerator.writeStringField("domaine", site.getDomaine());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
    }
}
