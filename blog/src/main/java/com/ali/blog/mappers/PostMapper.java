package com.ali.blog.mappers;

import com.ali.blog.dtos.AllPostDto;
import com.ali.blog.dtos.PostResponseDto;
import com.ali.blog.dtos.TagDto;
import com.ali.blog.entities.Post;

import java.util.stream.Collectors;

public class PostMapper {
    public static PostResponseDto PostEntityToPostResponse (Long id) {
        return PostResponseDto
                .builder()
                .postId(id)
                .message("The post is created successfully")
                .success(true)
                .build();
    }

    public static AllPostDto toAllPostDto (Post post) {
        return AllPostDto
                .builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getAuthor().getId())
                .category(post.getCategory().getName())
                .tags(
                        post.getTags()
                                .stream()
                                .map(tag -> new TagDto(tag.getId(), tag.getName()))
                                .collect(Collectors.toSet())
                )
                .build();
    }
}
