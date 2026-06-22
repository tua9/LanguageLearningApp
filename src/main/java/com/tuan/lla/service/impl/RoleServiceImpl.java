package com.tuan.lla.service.impl;

import com.tuan.lla.dto.request.RoleRequest;
import com.tuan.lla.dto.response.RoleResponse;
import com.tuan.lla.exception.ResourceNotFoundException;
import com.tuan.lla.model.Role;
import com.tuan.lla.repository.RoleRepository;
import com.tuan.lla.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public RoleResponse getById(Integer id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        return toResponse(role);
    }

    @Override
    @Transactional
    public RoleResponse create(RoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        return toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional
    public RoleResponse update(Integer id, RoleRequest request) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role", "id", id));
        role.setName(request.getName());
        return toResponse(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role", "id", id);
        }
        roleRepository.deleteById(id);
    }

    // ────────── mapper ──────────
    private RoleResponse toResponse(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
