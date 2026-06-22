package com.tuan.lla.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VocabularyResponse {

    private UUID id;
    private String word;
    private String wordType;
    private String definition;
    private String sampleSentence;
    private String imageUrl;
    private String audioUrl;
    private UUID topicId;
    private String topicTitle;
    private Instant createdAt;
    private Instant updatedAt;
}
