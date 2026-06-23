package com.tuan.lla.controller;

import com.tuan.lla.dto.request.LoginRequest;
import com.tuan.lla.dto.request.RegisterRequest;
import com.tuan.lla.dto.response.ApiResponse;
import com.tuan.lla.dto.response.LoginResponse;
import com.tuan.lla.dto.response.UserResponse;
import com.tuan.lla.service.AuthService;
import com.tuan.lla.service.UserService;
import com.tuan.lla.utils.JwtProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = authService.register(request);
        return ResponseEntity.ok(ApiResponse.success("User registered successfully", response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(ApiResponse.success("Login successful", response));
    }
}
