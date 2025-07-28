package com.example.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class UsaDataApiClient {

    private static final Logger logger = LogManager.getLogger(UsaDataApiClient.class);
    private final RestTemplate restTemplate;

    public UsaDataApiClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public String callUsaDataApi() {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://honolulu-api.datausa.io/tesseract/data.jsonrecords")
                    .queryParam("cube", "acs_yg_total_population_1")
                    .queryParam("drilldowns", "Year,Nation")
                    .queryParam("locale", "en")
                    .queryParam("measures", "Population");
            ResponseEntity<String> response = restTemplate.getForEntity(builder.toUriString(), String.class);
            return response.getBody();
        } catch (RestClientException e) {
            logger.error("Failed to fetch data from API", e);
            throw e;
        }
    }
}