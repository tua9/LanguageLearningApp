package com.tuan.lla.service;

import com.tuan.lla.dto.request.VocabularyRequest;
import com.tuan.lla.dto.response.VocabularyResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VocabularyService {

    List<VocabularyResponse> getAll();

    List<VocabularyResponse> getAllByTopicId(Long topicId);

    VocabularyResponse getById(Long id);

    /**
     * Tạo từ vựng mới. Nếu {@code image} không null thì upload lên Cloudinary
     * và lưu URL vào DB.
     */
    VocabularyResponse create(VocabularyRequest request, MultipartFile image);

    /**
     * Cập nhật từ vựng. Nếu {@code image} không null thì upload ảnh mới
     * và thay thế URL cũ trong DB.
     */
    VocabularyResponse update(Long id, VocabularyRequest request, MultipartFile image);

    void delete(Long id);
}
