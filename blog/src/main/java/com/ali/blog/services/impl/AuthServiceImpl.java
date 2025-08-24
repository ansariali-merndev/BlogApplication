package com.ali.blog.services.impl;

import com.ali.blog.config.JwtUtils;
import com.ali.blog.dtos.*;
import com.ali.blog.entities.User;
import com.ali.blog.mappers.AuthMapper;
import com.ali.blog.repositories.UserRepository;
import com.ali.blog.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    private final JwtUtils jwtUtils;

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

    @Override
    public ProfileDetailDto getProfile(HttpServletRequest request) {
        String token = null;
        if(request.getCookies() != null) {
            for(Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("authorization")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
        if(token != null && jwtUtils.isTokenValid(token)) {
            String email = jwtUtils.getUsernameFromToken(token);
            User user = userRepository.findByEmail(email).orElseThrow();
            return ProfileDetailDto.builder().email(user.getEmail()).id(user.getId()).success(true).build();
        } else  {
            return ProfileDetailDto.builder().success(false).build();
        }
    }

    @Override
    public CommonDto logout(HttpServletRequest request, HttpServletResponse response) {
        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if(cookie.getName().equals("authorization")) {
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    cookie.setValue("");
                    response.addCookie(cookie);
                }
            }
        }
        return new CommonDto(true, "successfully");
    }
}
