package com.tuan.lla.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {

    @NotBlank(message = "Role name is required")
    @Size(max = 20, message = "Role name must not exceed 20 characters")
    private String name;
}
