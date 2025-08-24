package com.ali.blog.services;

import com.ali.blog.dtos.*;
import com.ali.blog.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface AuthService {
    AuthResponseDto register (RegisterRequestDto dto);
    AuthResponseDto login (LoginRequestDto dto);
    ProfileDetailDto getProfile (HttpServletRequest request);
    CommonDto logout (HttpServletRequest request, HttpServletResponse response);
}
