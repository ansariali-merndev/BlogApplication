package com.ali.blog.controllers;

import com.ali.blog.config.JwtUtils;
import com.ali.blog.dtos.*;
import com.ali.blog.services.impl.AuthServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServiceImpl authService;
    private final JwtUtils jwtUtils;

    @GetMapping("/check")
    public String check () {
        return "Hello I am in Authentication Route";
    }

    @GetMapping("/logout")
    public ResponseEntity<CommonDto> logout (HttpServletRequest request, HttpServletResponse response) {
        try {
            CommonDto commonDto = authService.logout(request, response);
            return ResponseEntity.ok(commonDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(CommonDto.builder().success(false).message(e.getMessage()).build());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileDetailDto> getProfile (HttpServletRequest request) {
        try {
            ProfileDetailDto profileDetailDto = authService.getProfile(request);
            return ResponseEntity.ok(profileDetailDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register (@RequestBody RegisterRequestDto registerRequestDto, HttpServletResponse response) {
        try {
            AuthResponseDto authResponseDto = authService.register(registerRequestDto);
            String token = jwtUtils.generateToken(authResponseDto.getUserEmail(), authResponseDto.getId());
            Cookie cookie = new Cookie("authorization", token);
            cookie.setSecure(false);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return ResponseEntity.ok(authResponseDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(AuthResponseDto
                            .builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
                    );
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login (@RequestBody LoginRequestDto dto, HttpServletResponse response) {
        try {
            AuthResponseDto authResponseDto = authService.login(dto);
            String token = jwtUtils.generateToken(authResponseDto.getUserEmail(), authResponseDto.getId());
            Cookie cookie = new Cookie("authorization", token);
            cookie.setSecure(false);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return ResponseEntity.ok(authResponseDto);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(AuthResponseDto
                            .builder()
                            .success(false)
                            .message(e.getMessage())
                            .build()
                    );
        }
    }
}
