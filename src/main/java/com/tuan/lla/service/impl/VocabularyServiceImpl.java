package com.tuan.lla.service.impl;

import com.tuan.lla.client.DictionaryClient;
import com.tuan.lla.dto.request.VocabularyRequest;
import com.tuan.lla.dto.response.ExternalDictionaryResponse;
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

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VocabularyServiceImpl implements VocabularyService {
    private final VocabularyRepository vocabularyRepository;
    private final TopicRepository topicRepository;
    private final CloudinaryService cloudinaryService;
    private final DictionaryClient dictionaryClient;

    @Override
    public List<VocabularyResponse> getAll() {
        return vocabularyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public List<VocabularyResponse> getAllByTopicId(UUID topicId) {
        if (!topicRepository.existsById(topicId)) {
            throw new ResourceNotFoundException("Topic", "id", topicId);
        }
        return vocabularyRepository.findAllByTopicId(topicId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public VocabularyResponse getById(UUID id) {
        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vocabulary", "id", id));
        return toResponse(vocabulary);
    }

    @Override
    public VocabularyResponse searchByWord(String word) {
        ExternalDictionaryResponse externalData = dictionaryClient.getWord(word);

        String type = (externalData.pos() != null) ? externalData.pos().get(0) : null;

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

        String pronunciation = externalData.pronunciation().getFirst().pron();

        return VocabularyResponse.builder()
                .word(externalData.word())
                .wordType(type)
                .definition(defText)
                .pronunciation(pronunciation)
                .audioUrl(audio)
                .sampleSentence(sample)
                .build();
    }

    @Override
    @Transactional
    public VocabularyResponse create(VocabularyRequest request, MultipartFile image) {
        Topic topic = null;

        if (request.getTopicId() != null) {
            topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", request.getTopicId()));
        }

        String imageUrl = hasFile(image) ? cloudinaryService.uploadImage(image) : null;

        Vocabulary vocabulary = new Vocabulary();
        vocabulary.setWord(request.getWord());
        vocabulary.setWordType(request.getWordType());
        vocabulary.setMeaning(translate(request.getWord()));
        vocabulary.setPronunciation(request.getPronunciation());
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
    public VocabularyResponse update(UUID id, VocabularyRequest request, MultipartFile image) {
        Vocabulary vocabulary = vocabularyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vocabulary", "id", id));

        if (request.getTopicId() != null) {
            Topic topic = topicRepository.findById(request.getTopicId())
                    .orElseThrow(() -> new ResourceNotFoundException("Topic", "id", request.getTopicId()));
            vocabulary.setTopic(topic);
        }

        if (hasFile(image)) {
            vocabulary.setImageUrl(cloudinaryService.uploadImage(image));
        }

        vocabulary.setWord(request.getWord());
        vocabulary.setWordType(request.getWordType());
        vocabulary.setSampleSentence(request.getSampleSentence());
        vocabulary.setAudioUrl(request.getAudioUrl());
        vocabulary.setUpdatedAt(Instant.now());

        return toResponse(vocabularyRepository.save(vocabulary));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        if (!vocabularyRepository.existsById(id)) {
            throw new ResourceNotFoundException("Vocabulary", "id", id);
        }
        vocabularyRepository.deleteById(id);
    }

    // ────────── helpers ──────────

    private boolean hasFile(MultipartFile file) {
        return file != null && !file.isEmpty();
    }

    private String translate(String str) {

        String textToTranslate = str;

        String encodedText = URLEncoder.encode(textToTranslate, StandardCharsets.UTF_8);

        String urlString = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=vi&dt=t&q=" + encodedText;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String responseBody = response.body();
        String translatedText = responseBody.substring(responseBody.indexOf("\"") + 1, responseBody.indexOf("\","));

        return translatedText;
    }

    // ────────── mapper ──────────

    private VocabularyResponse toResponse(Vocabulary v) {
        return VocabularyResponse.builder()
                .id(v.getId())
                .word(v.getWord())
                .wordType(v.getWordType())
                .sampleSentence(v.getSampleSentence())
                .meaning(v.getMeaning())
                .pronunciation(v.getPronunciation())
                .imageUrl(v.getImageUrl())
                .audioUrl(v.getAudioUrl())
                .topicId(v.getTopic() != null ? v.getTopic().getId() : null)
                .topicTitle(v.getTopic() != null ? v.getTopic().getTitle() : null)
                .createdAt(v.getCreatedAt())
                .updatedAt(v.getUpdatedAt())
                .build();
    }
}
