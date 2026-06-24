package com.tuan.lla.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

<<<<<<< HEAD
=======
import java.util.UUID;

>>>>>>> dev
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleResponse {

<<<<<<< HEAD
    private Integer id;
=======
    private UUID id;
>>>>>>> dev
    private String name;
}
