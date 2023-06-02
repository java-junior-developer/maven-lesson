package ru.itmo.cats;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Request {
    private HttpRequest.Builder requestBuilder;
    private ObjectMapper mapper;
    private HttpClient client;

    public Request(URI requestUri) {
        requestBuilder = HttpRequest.newBuilder().uri(requestUri);
        client = HttpClient.newHttpClient();
        mapper = new ObjectMapper();
    }

    public List<Cat> getRequest() throws IOException, InterruptedException {
        HttpRequest request = requestBuilder.GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, Cat.class);

        return mapper.readValue(response.body(), listType);
    }

    public Cat postRequest(Cat cat) throws IOException, InterruptedException {
        HttpRequest request = requestBuilder.headers("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(cat)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(response.body(), Cat.class);
    }


}