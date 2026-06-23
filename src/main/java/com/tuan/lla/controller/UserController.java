package com.tuan.lla.controller;

import com.tuan.lla.dto.request.UserRequest;
import com.tuan.lla.dto.response.ApiResponse;
import com.tuan.lla.dto.response.UserResponse;
import com.tuan.lla.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> dev

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(userService.getAll()));
    }

    @GetMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long id) {
=======
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable UUID id) {
>>>>>>> dev
        return ResponseEntity.ok(ApiResponse.success(userService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> create(@Valid @RequestBody UserRequest request) {
        UserResponse created = userService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("User created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> update(
<<<<<<< HEAD
            @PathVariable Long id,
=======
            @PathVariable UUID id,
>>>>>>> dev
            @Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(ApiResponse.success("User updated successfully", userService.update(id, request)));
    }

    @DeleteMapping("/{id}")
<<<<<<< HEAD
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
=======
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
>>>>>>> dev
        userService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("User deleted successfully", null));
    }
}
