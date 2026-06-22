package com.tuan.lla.service;

import com.tuan.lla.dto.request.UserRequest;
import com.tuan.lla.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> getAll();

    UserResponse getById(Long id);

    UserResponse create(UserRequest request);

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);
}
