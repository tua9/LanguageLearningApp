package com.tuan.lla.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 100)
    private String title;

    @Size(max = 255)
    private String description;

    @NotNull(message = "User ID is required")
    private Long userId;
}
