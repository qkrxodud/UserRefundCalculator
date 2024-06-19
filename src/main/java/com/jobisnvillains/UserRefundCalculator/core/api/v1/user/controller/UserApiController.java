package com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller;

import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller.request.UserLoginParam;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.controller.request.UserParam;
import com.jobisnvillains.UserRefundCalculator.core.api.v1.user.domain.AccessToken;
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

@RestController
@RequestMapping("/szs")
@Tag(name = "User Controller", description = "회원 API")
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
    private final UserService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserParam userParam) {
        memberService.sinUp(userParam.toModel());
        return ResponseEntity.status(HttpStatus.CREATED).body("회원을 등록하는데 성공하였습니다.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginParam userLoginParam) {
        if (!memberService.login(userLoginParam.getUserId(), userLoginParam.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원을 찾을수 없습니다.");
        }
        AccessToken accessToken = new AccessToken();
        accessToken.addAccessToken("accessToken", jwtUtil.generateToken(userLoginParam.getUserId()));
        return ResponseEntity.ok(accessToken.getAccessTokens());
    }
}

//