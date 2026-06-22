package com.tuan.lla.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicResponse {

    private UUID id;
    private String title;
    private String description;
    private UUID userId;
    private String userFullName;
    private Instant createdAt;
    private Instant updatedAt;
}
