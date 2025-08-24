package com.ali.blog.mappers;

import com.ali.blog.dtos.AuthResponseDto;
import com.ali.blog.dtos.LoginRequestDto;
import com.ali.blog.dtos.RegisterRequestDto;
import com.ali.blog.entities.User;
import com.ali.blog.entities.type.Role;

import java.util.UUID;

public class AuthMapper {

    public static AuthResponseDto LoginDtoToAuthResponse (LoginRequestDto dto, UUID id) {
        return AuthResponseDto
                .builder()
                .success(true)
                .userEmail(dto.getEmail())
                .id(id)
                .message("LogIn successfully")
                .build();
    }

    public static AuthResponseDto userToAuthResponse (User user) {
        return AuthResponseDto
                .builder()
                .success(true)
                .message("User register or login successfully")
                .id(user.getId())
                .userEmail(user.getEmail())
                .build();
    }

    public static User RegisterRequestToUser (RegisterRequestDto dto) {
        return User
                .builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .role(Role.USER)
                .build();
    }
}
