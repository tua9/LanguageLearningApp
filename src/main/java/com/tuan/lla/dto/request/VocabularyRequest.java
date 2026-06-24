package com.tuan.lla.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class VocabularyRequest {

    @NotBlank(message = "Word is required")
    @Size(max = 100)
    private String word;

    @NotBlank(message = "Word type is required")
    @Size(max = 20)
    private String wordType;

    @NotBlank(message = "Definition is required")
    private String definition;

    private String sampleSentence;
    private String pronunciation;
    private String audioUrl;

    private UUID topicId;
}
