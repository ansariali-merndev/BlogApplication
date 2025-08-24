package com.ali.blog.services.impl;

import com.ali.blog.dtos.AuthResponseDto;
import com.ali.blog.dtos.LoginRequestDto;
import com.ali.blog.dtos.RegisterRequestDto;
import com.ali.blog.entities.User;
import com.ali.blog.mappers.AuthMapper;
import com.ali.blog.repositories.UserRepository;
import com.ali.blog.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDto register(RegisterRequestDto dto) {
        Optional<User> optionalUser = userRepository.findByEmail(dto.getEmail());
        if(optionalUser.isPresent()) {
            return AuthResponseDto
                    .builder()
                    .success(false)
                    .message("Email is already register, Please login to continue")
                    .build();
        }

        User user = AuthMapper.RegisterRequestToUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return AuthMapper.userToAuthResponse(saved);
    }

    @Override
    public AuthResponseDto login(LoginRequestDto dto) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        authenticationManager.authenticate(authentication);
        User user = userRepository.findByEmail(dto.getEmail()).orElseThrow();
        return AuthMapper.LoginDtoToAuthResponse(dto, user.getId());
    }
}
