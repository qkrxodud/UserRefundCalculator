package com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.controller;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.scrap.service.ScrapService;
import com.jobisnvillains.UserRefundCalculator.utils.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/szs")
@Tag(name = "Scrap Controller", description = "스크랩 API")
@RequiredArgsConstructor
@Slf4j
public class ScrapApiController {
    private final JwtUtil jwtUtil;
    private final ScrapService scrapService;

    @PostMapping("/scrap")
    public ResponseEntity<?> saveIncome(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String jwtToken = authorization.substring(7);
        scrapService.saveIncomeInfo(jwtUtil.extractUserId(jwtToken));
        return ResponseEntity.status(HttpStatus.CREATED).body("스크랩 저장에 성공하였습니다.");
    }

    @GetMapping("/refund")
    public ResponseEntity<?> calculateTax(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");
        String jwtToken = authorization.substring(7);
        String tax = scrapService.calculateTax(jwtUtil.extractUserId(jwtToken));
        Map<String, String> response = new HashMap<>();
        response.put("결정세액", tax);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
