package com.ali.blog.services;

import com.ali.blog.dtos.PostRequestDto;
import com.ali.blog.entities.Post;

public interface HelperService {
    Post createHelperPost (PostRequestDto dto);
}
