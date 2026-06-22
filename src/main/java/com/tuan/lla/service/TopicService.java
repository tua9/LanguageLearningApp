package com.tuan.lla.service;

import com.tuan.lla.dto.request.TopicRequest;
import com.tuan.lla.dto.response.TopicResponse;

import java.util.List;
import java.util.UUID;

public interface TopicService {

    List<TopicResponse> getAll();

    List<TopicResponse> getAllByUserId(UUID userId);

    TopicResponse getById(UUID id);

    TopicResponse create(TopicRequest request);

    TopicResponse update(UUID id, TopicRequest request);

    void delete(UUID id);
}
