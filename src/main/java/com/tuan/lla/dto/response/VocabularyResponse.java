package com.tuan.lla.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
<<<<<<< HEAD
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VocabularyResponse {

    private Long id;
=======
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VocabularyResponse {

    private UUID id;
>>>>>>> dev
    private String word;
    private String wordType;
    private String definition;
    private String sampleSentence;
    private String imageUrl;
    private String audioUrl;
<<<<<<< HEAD
    private Long topicId;
=======
    private UUID topicId;
>>>>>>> dev
    private String topicTitle;
    private Instant createdAt;
    private Instant updatedAt;
}
