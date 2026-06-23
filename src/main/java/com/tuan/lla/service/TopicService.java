package com.tuan.lla.service;

import com.tuan.lla.dto.request.TopicRequest;
import com.tuan.lla.dto.response.TopicResponse;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> dev

public interface TopicService {

    List<TopicResponse> getAll();

<<<<<<< HEAD
    List<TopicResponse> getAllByUserId(Long userId);

    TopicResponse getById(Long id);

    TopicResponse create(TopicRequest request);

    TopicResponse update(Long id, TopicRequest request);

    void delete(Long id);
=======
    List<TopicResponse> getAllByUserId(UUID userId);

    TopicResponse getById(UUID id);

    TopicResponse create(TopicRequest request);

    TopicResponse update(UUID id, TopicRequest request);

    void delete(UUID id);
>>>>>>> dev
}
