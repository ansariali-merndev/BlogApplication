package com.ali.blog.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllPostDto {
    private Long postId;
    private String title;
    private String content;
    private UUID userId;
    private String category;
    private Set<TagDto> tags = new HashSet<>();
}
