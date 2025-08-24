package com.ali.blog.controllers;

import com.ali.blog.entities.User;
import com.ali.blog.services.impl.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminServiceImpl adminService;

    @GetMapping
    public List<User> getAllUser () {
        return adminService.getAllUser();
    }
}
