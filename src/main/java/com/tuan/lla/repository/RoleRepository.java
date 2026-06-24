package com.tuan.lla.repository;

import com.tuan.lla.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
<<<<<<< HEAD

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
=======
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
>>>>>>> dev

    Optional<Role> findByName(String name);
}
