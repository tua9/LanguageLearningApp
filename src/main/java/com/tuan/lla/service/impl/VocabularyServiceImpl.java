package com.tuan.lla.service.impl;

import com.tuan.lla.dto.request.VocabularyRequest;
import com.tuan.lla.dto.response.VocabularyResponse;
import com.tuan.lla.exception.ResourceNotFoundException;
import com.tuan.lla.model.Topic;
import com.tuan.lla.model.Vocabulary;
import com.tuan.lla.repository.TopicRepository;
import com.tuan.lla.repository.VocabularyRepository;
import com.tuan.lla.service.CloudinaryService;
import com.tuan.lla.service.VocabularyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VocabularyServiceImpl implements VocabularyService {

    private final VocabularyRepository vocabularyRepository;
    private final TopicRepository topicRepository;
    private final CloudinaryService cloudinaryService;

    @Override
    public List<VocabularyResponse> getAll() {
        return vocabularyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<VocabularyResponse> getAllByTopicId(Long topicId) {
        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException("Topic", "id", topicId);
        }
        return vocabularyRepository.findAllByTopicId(topicId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public VocabularyResponse getById(Long id) {
        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vocabulary", "id", id));
        return toResponse(vocabulary);
    }

    @Override
    @Transactional
    public VocabularyResponse create(VocabularyRequest request, MultipartFile image) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", request.getTopicId()));

        String imageUrl = hasFile(image) ? cloudinaryService.uploadImage(image) : null;

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(request.getWord());
        vocabulary.setWordType(request.getWordType());
        vocabulary.setDefinition(request.getDefinition());
        vocabulary.setSampleSentence(request.getSampleSentence());
        vocabulary.setImageUrl(imageUrl);
        vocabulary.setAudioUrl(request.getAudioUrl());
        vocabulary.setTopic(topic);
        vocabulary.setCreatedAt(Instant.now());
        vocabulary.setUpdatedAt(Instant.now());

        return toResponse(vocabularyRepository.save(vocabulary));
    }

    @Override
    @Transactional
    public VocabularyResponse update(Long id, VocabularyRequest request, MultipartFile image) {
        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vocabulary", "id", id));

        if (request.getTopicId() != null) {
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", request.getTopicId()));
            vocabulary.setTopic(topic);
        }

        // Chỉ thay ảnh khi client gửi file mới, giữ nguyên URL cũ nếu không có ảnh
        if (hasFile(image)) {
            vocabulary.setImageUrl(cloudinaryService.uploadImage(image));
        }

        vocabulary.setWord(request.getWord());
        vocabulary.setWordType(request.getWordType());
        vocabulary.setDefinition(request.getDefinition());
        vocabulary.setSampleSentence(request.getSampleSentence());
        vocabulary.setAudioUrl(request.getAudioUrl());
        vocabulary.setUpdatedAt(Instant.now());

        return toResponse(vocabularyRepository.save(vocabulary));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!vocabularyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vocabulary", "id", id);
        }
        vocabularyRepository.deleteById(id);
    }

    // ────────── helpers ──────────

    private boolean hasFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }

    // ────────── mapper ──────────

    private VocabularyResponse toResponse(Vocabulary v) {
        return VocabularyResponse.builder()
                .id(v.getId())
                .word(v.getWord())
                .wordType(v.getWordType())
                .definition(v.getDefinition())
                .sampleSentence(v.getSampleSentence())
                .imageUrl(v.getImageUrl())
                .audioUrl(v.getAudioUrl())
                .topicId(v.getTopic() != null ? v.getTopic().getId() : null)
                .topicTitle(v.getTopic() != null ? v.getTopic().getTitle() : null)
                .createdAt(v.getCreatedAt())
                .updatedAt(v.getUpdatedAt())
                .build();
    }
}
