package com.krizsanandras.projectwob;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class GetData {

    // gets the data from the API as JsonNode
    public JsonNode getRestData(String toGet) {

        final String uri = "https://my.api.mockaroo.com/" + toGet + "?key=63304c70";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        final ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResult = null;

        try {
            jsonResult = objectMapper.readValue(result, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }
}

