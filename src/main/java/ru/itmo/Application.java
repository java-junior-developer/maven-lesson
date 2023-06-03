package ru.itmo;


import ru.itmo.cats.Cat;
import ru.itmo.cats.Category;
import ru.itmo.cats.Request;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Application {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        Request<Category> categoryRequest = new Request<>(new URI("http://localhost:8080/categories"), Category.class);
        System.out.println(categoryRequest.get());

        Request<Cat> catRequest = new Request<>(new URI("http://localhost:8080/cats"), Cat.class);
        System.out.println(catRequest.get());

    }
}
