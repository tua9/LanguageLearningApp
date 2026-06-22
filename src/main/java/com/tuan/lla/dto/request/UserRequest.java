package com.tuan.lla.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100)
    private String email;

    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    private String password;

    @NotBlank(message = "Full name is required")
    @Size(max = 100)
    private String fullName;

    private String avatarUrl;

    @Size(max = 20)
    private String authProvider;

    @Size(max = 255)
    private String providerId;

    @NotNull(message = "RoleID is required")
    private Integer roleId;
}
