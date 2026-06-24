package com.tuan.lla.service.impl;

import com.tuan.lla.dto.request.TopicRequest;
import com.tuan.lla.dto.response.TopicResponse;
import com.tuan.lla.exception.ResourceNotFoundException;
import com.tuan.lla.model.Topic;
import com.tuan.lla.model.User;
import com.tuan.lla.repository.TopicRepository;
import com.tuan.lla.repository.UserRepository;
import com.tuan.lla.service.TopicService;
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
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    @Override
    public List<TopicResponse> getAll() {
        return topicRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
<<<<<<< HEAD
    public List<TopicResponse> getAllByUserId(Long userId) {
=======
    public List<TopicResponse> getAllByUserId(UUID userId) {
>>>>>>> dev
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User", "id", userId);
        }
        return topicRepository.findAllByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
<<<<<<< HEAD
    public TopicResponse getById(Long id) {
=======
    public TopicResponse getById(UUID id) {
>>>>>>> dev
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", id));
        return toResponse(topic);
    }

    @Override
    @Transactional
    public TopicResponse create(TopicRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));

        Topic topic = new Topic();
        topic.setTitle(request.getTitle());
        topic.setDescription(request.getDescription());
        topic.setUser(user);
        topic.setCreatedAt(Instant.now());
        topic.setUpdatedAt(Instant.now());

        return toResponse(topicRepository.save(topic));
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public TopicResponse update(Long id, TopicRequest request) {
=======
    public TopicResponse update(UUID id, TopicRequest request) {
>>>>>>> dev
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", id));

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getUserId()));
            topic.setUser(user);
        }

        topic.setTitle(request.getTitle());
        topic.setDescription(request.getDescription());
        topic.setUpdatedAt(Instant.now());

        return toResponse(topicRepository.save(topic));
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public void delete(Long id) {
=======
    public void delete(UUID id) {
>>>>>>> dev
        if (!topicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Topic", "id", id);
        }
        topicRepository.deleteById(id);
    }

    // ────────── mapper ──────────
    private TopicResponse toResponse(Topic topic) {
        return TopicResponse.builder()
                .id(topic.getId())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .userId(topic.getUser() != null ? topic.getUser().getId() : null)
                .userFullName(topic.getUser() != null ? topic.getUser().getFullName() : null)
                .createdAt(topic.getCreatedAt())
                .updatedAt(topic.getUpdatedAt())
                .build();
    }
}
