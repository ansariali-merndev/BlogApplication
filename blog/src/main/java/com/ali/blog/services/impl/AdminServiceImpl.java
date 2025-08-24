package com.ali.blog.services.impl;

import com.ali.blog.entities.User;
import com.ali.blog.repositories.UserRepository;
import com.ali.blog.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
