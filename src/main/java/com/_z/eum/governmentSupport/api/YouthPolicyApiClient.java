package com._z.eum.governmentSupport.api;

import com._z.eum.governmentSupport.dto.YouthPolicyResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

@Component
public class YouthPolicyApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${YOUTH_API_KEY}")
    private String apiKey;

    public List<YouthPolicyResponse> fetchYouthPolicies() {
        String url = "https://www.youthcenter.go.kr/go/ythip/getPlcy" +
                "?apiKeyNm=" + apiKey +
                "&rtnType=json&pageSize=20" +
                "&zipCd=11000";

        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, JsonNode.class
            );

            JsonNode root = response.getBody();
            JsonNode policyList = root.path("result").path("youthPolicyList");

            List<YouthPolicyResponse> result = new ArrayList<>();
            for (JsonNode item : policyList) {
                result.add(new YouthPolicyResponse(
                        item.path("plcyNo").asText(),
                        item.path("plcyNm").asText(),
                        item.path("plcyExplnCn").asText(),
                        item.path("lclsfNm").asText(),
                        item.path("sprtTrgtMinAge").asText() + "~" + item.path("sprtTrgtMaxAge").asText(),
                        item.path("zipCd").asText(),
                        item.path("aplyUrlAddr").asText()
                ));
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}

