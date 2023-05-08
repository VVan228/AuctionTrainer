package ru.isu.auc.messaging.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import ru.isu.auc.messaging.model.Params;
import ru.isu.auc.messaging.model.PublishNotification;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MessagingServiceImpl implements MessagingService{

    @Value("${centrifugo.api_key}")
    String apiKey;
    @Value("${centrifugo.api_url}")
    String apiUrl;
    Gson gson = new Gson();

    @Override
    public void sendNotification(Object data, String channel){

        PublishNotification notification = new PublishNotification();
        notification.setMethod("publish");
        Params params = new Params();
        params.setChannel(channel);
        params.setData(data);
        notification.setParams(params);


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(notification)))
            .header("Authorization", "apikey " + apiKey)
            .build();

        try {
            HttpResponse<String> res = client.send(request,
                HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }
}
