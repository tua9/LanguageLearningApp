package com.tuan.lla.service;

import com.tuan.lla.dto.request.LoginRequest;
import com.tuan.lla.dto.request.RegisterRequest;
import com.tuan.lla.dto.response.LoginResponse;
import com.tuan.lla.dto.response.UserResponse;

public interface AuthService {
    UserResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
