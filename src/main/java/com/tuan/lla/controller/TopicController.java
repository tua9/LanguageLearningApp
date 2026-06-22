package com.tuan.lla.controller;

import com.tuan.lla.dto.request.TopicRequest;
import com.tuan.lla.dto.response.ApiResponse;
import com.tuan.lla.dto.response.TopicResponse;
import com.tuan.lla.service.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TopicResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(topicService.getAll()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<TopicResponse>>> getAllByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(ApiResponse.success(topicService.getAllByUserId(userId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(topicService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TopicResponse>> create(@Valid @RequestBody TopicRequest request) {
        TopicResponse created = topicService.create(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Topic created successfully", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TopicResponse>> update(
            @PathVariable UUID id,
            @Valid @RequestBody TopicRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Topic updated successfully", topicService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        topicService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Topic deleted successfully", null));
    }
}
