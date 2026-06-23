package com.tuan.lla.service.impl;

<<<<<<< HEAD
import com.tuan.lla.dto.request.VocabularyRequest;
=======
import com.tuan.lla.client.DictionaryClient;
import com.tuan.lla.dto.request.VocabularyRequest;
import com.tuan.lla.dto.response.ExternalDictionaryResponse;
>>>>>>> dev
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
<<<<<<< HEAD
=======
import org.springframework.web.client.RestClient;
>>>>>>> dev
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Map;
import java.util.UUID;
>>>>>>> dev

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VocabularyServiceImpl implements VocabularyService {
<<<<<<< HEAD

    private final VocabularyRepository vocabularyRepository;
    private final TopicRepository topicRepository;
    private final CloudinaryService cloudinaryService;
=======
    private final VocabularyRepository vocabularyRepository;
    private final TopicRepository topicRepository;
    private final CloudinaryService cloudinaryService;
    private final DictionaryClient dictionaryClient;
>>>>>>> dev

    @Override
    public List<VocabularyResponse> getAll() {
        return vocabularyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
<<<<<<< HEAD
    public List<VocabularyResponse> getAllByTopicId(Long topicId) {
=======
    public List<VocabularyResponse> getAllByTopicId(UUID topicId) {
>>>>>>> dev
        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException("Topic", "id", topicId);
        }
        return vocabularyRepository.findAllByTopicId(topicId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
<<<<<<< HEAD
    public VocabularyResponse getById(Long id) {
=======
    public VocabularyResponse getById(UUID id) {
>>>>>>> dev
        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vocabulary", "id", id));
        return toResponse(vocabulary);
    }

    @Override
<<<<<<< HEAD
    @Transactional
    public VocabularyResponse create(VocabularyRequest request, MultipartFile image) {
        Topic topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", request.getTopicId()));
=======
    public VocabularyResponse searchByWord(String word) {
        ExternalDictionaryResponse externalData = dictionaryClient.getWord(word);

        String type = (externalData.pos() != null) ? String.join(" ,", externalData.pos()) : null;

        String defText = (externalData.definition() != null && !externalData.definition().isEmpty())
                ? externalData.definition().get(0).text() : null;

        String audio = (externalData.pronunciation() != null && !externalData.pronunciation().isEmpty())
                ? externalData.pronunciation().get(0).url() : null;

        String sample = null;
        if (externalData.definition() != null && !externalData.definition().isEmpty()) {
            var examples = externalData.definition().get(0).example();
            if (examples != null && !examples.isEmpty()) {
                sample = examples.get(0).text();
            }
        }

        return VocabularyResponse.builder()
                .word(externalData.word())
                .wordType(type)
                .definition(defText)
                .audioUrl(audio)
                .sampleSentence(sample)
                .build();
    }

    @Override
    @Transactional
    public VocabularyResponse create(VocabularyRequest request, MultipartFile image) {
        Topic topic = null;

        if (request.getTopicId() != null) {
            topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", request.getTopicId()));
        }
>>>>>>> dev

        String imageUrl = hasFile(image) ? cloudinaryService.uploadImage(image) : null;

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(request.getWord());
        vocabulary.setWordType(request.getWordType());
<<<<<<< HEAD
        vocabulary.setDefinition(request.getDefinition());
=======
>>>>>>> dev
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
<<<<<<< HEAD
    public VocabularyResponse update(Long id, VocabularyRequest request, MultipartFile image) {
=======
    public VocabularyResponse update(UUID id, VocabularyRequest request, MultipartFile image) {
>>>>>>> dev
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
<<<<<<< HEAD
        vocabulary.setDefinition(request.getDefinition());
=======
>>>>>>> dev
        vocabulary.setSampleSentence(request.getSampleSentence());
        vocabulary.setAudioUrl(request.getAudioUrl());
        vocabulary.setUpdatedAt(Instant.now());

        return toResponse(vocabularyRepository.save(vocabulary));
    }

    @Override
    @Transactional
<<<<<<< HEAD
    public void delete(Long id) {
=======
    public void delete(UUID id) {
>>>>>>> dev
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
<<<<<<< HEAD
                .definition(v.getDefinition())
=======
>>>>>>> dev
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
