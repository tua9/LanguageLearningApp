package com.tuan.lla.service.impl;

import com.tuan.lla.dto.request.UserRequest;
import com.tuan.lla.dto.response.UserResponse;
import com.tuan.lla.exception.ResourceNotFoundException;
import com.tuan.lla.model.Role;
import com.tuan.lla.model.User;
import com.tuan.lla.repository.RoleRepository;
import com.tuan.lla.repository.UserRepository;
import com.tuan.lla.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> dev

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
<<<<<<< HEAD
    public UserResponse getById(Long id) {
=======
    public UserResponse getById(UUID id) {
>>>>>>> dev
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return toResponse(user);
    }

    @Override
    @Transactional
    public UserResponse create(UserRequest request) {
        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", request.getRoleId()));

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setFullName(request.getFullName());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setAuthProvider(request.getAuthProvider());
        user.setProviderId(request.getProviderId());
        user.setRole(role);
        user.setCreatedAt(Instant.now());
        user.setUpdatedAt(Instant.now());

        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public UserResponse update(Long id, UserRequest request) {
=======
    public UserResponse update(UUID id, UserRequest request) {
>>>>>>> dev
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        if (request.getRoleId() != null) {
            Role role = roleRepository.findById(request.getRoleId())
                    .orElseThrow(() -> new ResourceNotFoundException("Role", "id", request.getRoleId()));
            user.setRole(role);
        }

        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setAvatarUrl(request.getAvatarUrl());
        user.setAuthProvider(request.getAuthProvider());
        user.setProviderId(request.getProviderId());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(request.getPassword());
        }
        user.setUpdatedAt(Instant.now());

        return toResponse(userRepository.save(user));
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public void delete(Long id) {
=======
    public void delete(UUID id) {
>>>>>>> dev
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }

    // ────────── mapper ──────────
    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .avatarUrl(user.getAvatarUrl())
                .authProvider(user.getAuthProvider())
                .roleName(user.getRole() != null ? user.getRole().getName() : null)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
