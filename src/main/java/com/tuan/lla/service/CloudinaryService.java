package com.tuan.lla.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    /**
     * Upload ảnh lên Cloudinary.
     *
     * @param file file ảnh cần upload
     * @return secure URL của ảnh sau khi upload thành công
     */
    String uploadImage(MultipartFile file);

    /**
     * Xoá ảnh khỏi Cloudinary theo publicId.
     *
     * @param publicId public_id trả về lúc upload
     */
    void deleteImage(String publicId);
}
