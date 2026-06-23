package com.tuan.lla.service.impl;

import com.tuan.lla.dto.request.LoginRequest;
import com.tuan.lla.dto.request.RegisterRequest;
import com.tuan.lla.dto.response.LoginResponse;
import com.tuan.lla.dto.response.UserResponse;
import com.tuan.lla.model.Role;
import com.tuan.lla.model.User;
import com.tuan.lla.repository.RoleRepository;
import com.tuan.lla.repository.UserRepository;
import com.tuan.lla.utils.JwtProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthServiceImpl authService;

    private Role defaultRole;

    @BeforeEach
    void setUp() {
        defaultRole = new Role();
        defaultRole.setId(UUID.randomUUID());
        defaultRole.setName("ROLE_USER");
    }

    @Test
    void register_success() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");
        request.setFullName("Test User");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(defaultRole));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(UUID.randomUUID());
            return user;
        });

        UserResponse response = authService.register(request);

        assertNotNull(response);
        assertEquals(request.getEmail(), response.getEmail());
        assertEquals(request.getFullName(), response.getFullName());
        assertEquals("ROLE_USER", response.getRoleName());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void register_emailExists_throwsException() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("existing@example.com");
        request.setPassword("password123");
        request.setFullName("Existing User");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> authService.register(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void login_success() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password123");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setFullName("Test User");
        user.setRole(defaultRole);

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(jwtProvider.generateToken(any(UserResponse.class))).thenReturn("mocked-jwt-token");

        LoginResponse response = authService.login(request);

        assertNotNull(response);
        assertEquals("mocked-jwt-token", response.getToken());
        assertEquals(request.getEmail(), response.getUser().getEmail());
    }

    @Test
    void login_wrongPassword_throwsException() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("wrong-password");

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw("correct-password", BCrypt.gensalt()));
        user.setFullName("Test User");
        user.setRole(defaultRole);

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> authService.login(request));
    }
}
