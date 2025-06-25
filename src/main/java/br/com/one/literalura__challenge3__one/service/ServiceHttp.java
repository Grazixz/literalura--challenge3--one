package br.com.one.literalura__challenge3__one.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServiceHttp {
    private String bodyResponse;

    public String getBodyResponse() {
        return bodyResponse;
    }

    public void catchData(String address){
        try {
            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            bodyResponse = response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
