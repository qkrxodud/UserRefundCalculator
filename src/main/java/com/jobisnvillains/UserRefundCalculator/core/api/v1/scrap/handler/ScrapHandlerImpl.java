package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.handler;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.domain.Income;
import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.EmptyAPIDataStateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScrapHandlerImpl implements ScrapHandler {
    private final RestTemplate restTemplate;

    @Value("${scrap.api-url}")
    private String apiUrl;
    @Value("${scrap.api-key}")
    private String apiKey;

    public Income getScrapIncomeInfo(String name, String regNo) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("X-API-KEY", apiKey);

        Income scrapResponse = null;
        Map<String, String> requestData = new HashMap<>();
        requestData.put("name", name);
        requestData.put("regNo", regNo);

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
            log.error("API >> getScrapIncomeInfo >> getScrapIncomeInfo {}, name={}, reg={}", e.getMessage(), name, regNo);
        } catch (RuntimeException e) {
            log.error("API >> getScrapIncomeInfo >> getScrapIncomeInfo {}, name={}, reg={}", e.getMessage(), name, regNo);
        }

        return Optional.ofNullable(scrapResponse)
                .orElseThrow(() -> new EmptyAPIDataStateException("API 데이터가 비워있습니다."));

    }
}
