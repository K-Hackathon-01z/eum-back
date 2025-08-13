package com._z.eum.governmentSupport.api;

import com._z.eum.governmentSupport.dto.YouthPolicyResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Component
public class YouthPolicyApiClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "973ec1bd-c85a-4f07-98f7-ad0241070426";

    public List<YouthPolicyResponse> fetchYouthPolicies() {
        String url = "https://www.youthcenter.go.kr/go/ythip/getPlcy" +
                "?apiKeyNm=" + API_KEY +
                "&rtnType=json&pageSize=50" +
                "&zipCd=11000";

        try {
            ResponseEntity<JsonNode> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, JsonNode.class
            );

            JsonNode root = response.getBody();
            JsonNode policyList = root.path("youthPolicyList");

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

