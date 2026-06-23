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
public class UserResponse {

<<<<<<< HEAD
    private Long id;
=======
    private UUID id;
>>>>>>> dev
    private String email;
    private String fullName;
    private String avatarUrl;
    private String authProvider;
    private String roleName;
    private Instant createdAt;
    private Instant updatedAt;
}
