package com.tuan.lla.controller;

import com.tuan.lla.dto.request.VocabularyRequest;
import com.tuan.lla.dto.response.ApiResponse;
import com.tuan.lla.dto.response.VocabularyResponse;
import com.tuan.lla.service.CloudinaryService;
import com.tuan.lla.service.VocabularyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vocabularies")
@RequiredArgsConstructor
public class VocabularyController {

    private final VocabularyService vocabularyService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<VocabularyResponse>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(vocabularyService.getAll()));
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<ApiResponse<List<VocabularyResponse>>> getAllByTopic(@PathVariable UUID topicId) {
        return ResponseEntity.ok(ApiResponse.success(vocabularyService.getAllByTopicId(topicId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VocabularyResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(vocabularyService.getById(id)));
    }

    @GetMapping("/find/{word}")
    public ResponseEntity<ApiResponse<VocabularyResponse>> searchWord(@PathVariable String word) {
        return ResponseEntity.ok(ApiResponse.success(vocabularyService.searchByWord(word)));
    }

    /**
     * Tạo từ vựng mới kèm ảnh (tuỳ chọn).
     * Content-Type: multipart/form-data
     * - Part "data"  : JSON VocabularyRequest
     * - Part "image" : file ảnh (optional)
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<VocabularyResponse>> create(
            @RequestPart("data") @Valid VocabularyRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        VocabularyResponse created = vocabularyService.create(request, image);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Vocabulary created successfully", created));
    }

    /**
     * Cập nhật từ vựng. Gửi file "image" mới nếu muốn thay ảnh, bỏ qua nếu không cần đổi.
     * Content-Type: multipart/form-data
     * - Part "data"  : JSON VocabularyRequest
     * - Part "image" : file ảnh mới (optional)
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<VocabularyResponse>> update(
            @PathVariable UUID id,
            @RequestPart("data") @Valid VocabularyRequest request,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        return ResponseEntity
                .ok(ApiResponse.success("Vocabulary updated successfully", vocabularyService.update(id, request, image)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        vocabularyService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Vocabulary deleted successfully", null));
    }
}
