package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AccessTest {

    @Test
    public void testExampleDotComAccessible() throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://examgplke.com"))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        int status = response.statusCode();

        System.out.println("HTTP status for https://example.com : " + status);
        // Force the test to fail (user requested)
        Assertions.fail("Forced failure for testing. HTTP status: " + status);
    }
}
