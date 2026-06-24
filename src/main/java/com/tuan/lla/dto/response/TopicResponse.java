package com.tuan.lla.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> dev

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicResponse {

<<<<<<< HEAD
    private Long id;
    private String title;
    private String description;
    private Long userId;
=======
    private UUID id;
    private String title;
    private String description;
    private UUID userId;
>>>>>>> dev
    private String userFullName;
    private Instant createdAt;
    private Instant updatedAt;
}
