package com.tuan.lla.service;

import com.tuan.lla.dto.request.RoleRequest;
import com.tuan.lla.dto.response.RoleResponse;

import java.util.List;
<<<<<<< HEAD
=======
import java.util.UUID;
>>>>>>> dev

public interface RoleService {

    List<RoleResponse> getAll();

<<<<<<< HEAD
    RoleResponse getById(Integer id);

    RoleResponse create(RoleRequest request);

    RoleResponse update(Integer id, RoleRequest request);

    void delete(Integer id);
=======
    RoleResponse getById(UUID id);

    RoleResponse create(RoleRequest request);

    RoleResponse update(UUID id, RoleRequest request);

    void delete(UUID id);
>>>>>>> dev
}
