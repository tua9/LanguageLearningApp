package com.tuan.lla.service;

import com.tuan.lla.dto.request.TopicRequest;
import com.tuan.lla.dto.response.TopicResponse;

import java.util.List;

public interface TopicService {

    List<TopicResponse> getAll();

    List<TopicResponse> getAllByUserId(Long userId);

    TopicResponse getById(Long id);

    TopicResponse create(TopicRequest request);

    TopicResponse update(Long id, TopicRequest request);

    void delete(Long id);
}
