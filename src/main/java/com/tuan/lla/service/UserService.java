package com.tuan.lla.service;

import com.tuan.lla.dto.request.UserRequest;
import com.tuan.lla.dto.response.UserResponse;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> dev

public interface UserService {

    List<UserResponse> getAll();

<<<<<<< HEAD
    UserResponse getById(Long id);

    UserResponse create(UserRequest request);

    UserResponse update(Long id, UserRequest request);

    void delete(Long id);
=======
    UserResponse getById(UUID id);

    UserResponse create(UserRequest request);

    UserResponse update(UUID id, UserRequest request);

    void delete(UUID id);
>>>>>>> dev
}
