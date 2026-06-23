package com.tuan.lla.repository;

import com.tuan.lla.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
<<<<<<< HEAD

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
=======
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
>>>>>>> dev

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
