package com.ashman.sample.api.switchgames;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SwitchGamesClient {

    public ResponseEntity<List<Response>> callGetSwitchGamesList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange("https://api.sampleapis.com/switch/games", HttpMethod.GET,
                null, new ParameterizedTypeReference<List<Response>>() {});
    }

}
