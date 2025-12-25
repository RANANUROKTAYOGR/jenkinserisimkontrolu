package test.java.com.example;

import org.junit.Test;

import static org.junit.api.Assertions.*;

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
                .uri(new URI("https://example.com"))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        int status = response.statusCode();

        System.out.println("HTTP status for https://example.com : " + status);
        // Assert that the status is 2xx
        Assertions.assertTrue(status >= 200 && status < 300, "Expected 2xx response from example.com but got: " + status);
    }
}
