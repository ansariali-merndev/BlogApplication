package com.ali.blog.services;

import com.ali.blog.dtos.AuthResponseDto;
import com.ali.blog.dtos.LoginRequestDto;
import com.ali.blog.dtos.RegisterRequestDto;
import com.ali.blog.entities.User;

import java.util.List;

public interface AuthService {
    AuthResponseDto register (RegisterRequestDto dto);
    AuthResponseDto login (LoginRequestDto dto);
}
