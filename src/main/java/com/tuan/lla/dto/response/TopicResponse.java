package com.tuan.lla.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TopicResponse {

    private Long id;
    private String title;
    private String description;
    private Long userId;
    private String userFullName;
    private Instant createdAt;
    private Instant updatedAt;
}
