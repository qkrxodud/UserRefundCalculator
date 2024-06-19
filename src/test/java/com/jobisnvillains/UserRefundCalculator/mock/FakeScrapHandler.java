package com.jobisnvillains.UserRefundCalculator.mock;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.Income;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler.port.ScrapHandler;
import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.EmptyAPIDataStateException;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class FakeScrapHandler implements ScrapHandler {
    private final String apiUrl= "https://codetest-v4.3o3.co.kr/scrap";
    private final String apiKey = "aXC8zK6puHIf9l53L8TiQg==";

    @Override
    public Income getScrapIncomeInfo(String name, String regNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-API-KEY", apiKey);

        Map<String, String> requestData = new HashMap<>();
        requestData.put("name", name);
        requestData.put("regNo", regNo);

        Income scrapResponse = null;
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl);
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestData, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.POST,
                    entity,
                    Map.class
            );
            if (response.getStatusCode() == HttpStatus.OK) {
                scrapResponse = new Income(response.getBody());
            }
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println("HttpClientErrorException");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException");
        }

        return Optional.ofNullable(scrapResponse)
                .orElseThrow(() -> new EmptyAPIDataStateException("API 데이터가 비워있습니다."));
    }
}
