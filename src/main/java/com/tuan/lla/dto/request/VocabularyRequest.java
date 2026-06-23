package com.tuan.lla.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

<<<<<<< HEAD
=======
import java.util.UUID;

>>>>>>> dev
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

    // imageUrl không có ở đây — ảnh được upload qua MultipartFile, URL do server tự set
    private String audioUrl;

<<<<<<< HEAD
    @NotNull(message = "Topic ID is required")
    private Long topicId;
=======
    private UUID topicId;
>>>>>>> dev
}
