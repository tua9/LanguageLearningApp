package com.tuan.lla.service;

import com.tuan.lla.dto.request.RoleRequest;
import com.tuan.lla.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {

    List<RoleResponse> getAll();

    RoleResponse getById(Integer id);

    RoleResponse create(RoleRequest request);

    RoleResponse update(Integer id, RoleRequest request);

    void delete(Integer id);
}
