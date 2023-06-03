package ru.itmo.cats;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Request<T> {
    private HttpRequest.Builder requestBuilder;
    private HttpClient client;
    private ObjectMapper mapper;
    private Class<T> clazz;

    public Request(URI requestUri, Class<T> clazz) {
        requestBuilder = HttpRequest.newBuilder().uri(requestUri);
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
        this.clazz = clazz;
    }

    public List<T> get() throws IOException, InterruptedException {
        HttpRequest request = requestBuilder.GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);

        return mapper.readValue(response.body(), listType);
    }

    public T post(T object) throws IOException, InterruptedException {
        HttpRequest request = requestBuilder.headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(object)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), clazz);
    }


}