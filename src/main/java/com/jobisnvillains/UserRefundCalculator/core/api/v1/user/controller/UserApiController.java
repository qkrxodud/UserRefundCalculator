package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller.param.UserLoginParam;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller.param.UserParam;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.User;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.service.UserService;
import com.jobisnvillains.UserRefundCalculator.utils.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/szs")
@Tag(name = "User Controller", description = "회원 가입 API")
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
    private final UserService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserParam userParam) {
        memberService.sinUp(userParam.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginParam userLoginParam) {
        if (memberService.login(userLoginParam.getUserId(), userLoginParam.getPassword())) {
            String token = jwtUtil.generateToken(userLoginParam.getUserId());
            Map<String, String> response = new HashMap<>();
            response.put("accessToken", token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }
}

//