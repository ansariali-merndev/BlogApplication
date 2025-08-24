package com.ali.blog.services.impl;

import com.ali.blog.dtos.PostRequestDto;
import com.ali.blog.entities.Category;
import com.ali.blog.entities.Post;
import com.ali.blog.entities.Tag;
import com.ali.blog.entities.User;
import com.ali.blog.repositories.CategoryRepository;
import com.ali.blog.repositories.TagRepository;
import com.ali.blog.repositories.UserRepository;
import com.ali.blog.services.HelperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomHelperService implements HelperService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;


    @Override
    public Post createHelperPost(PostRequestDto dto) {
        Post post = new Post();
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        User user = userRepository.findById(dto.getUser_id()).orElseThrow();
        post.setAuthor(user);

        Category category = categoryRepository.findByName(dto.getCategory());
        if (category == null) {
            category = categoryRepository.save(Category
                    .builder()
                    .name(dto.getCategory())
                    .build()
            );
        }
        post.setCategory(category);

        Set<Tag> tags = new HashSet<>();
        if(dto.getTagNames() != null) {
            for(String tagName : dto.getTagNames()) {
                Tag tag = tagRepository.findByName(tagName);
                if(tag == null) {
                    tag = tagRepository.save(Tag.builder().name(tagName).build());
                }
                tags.add(tag);
            }
        }
        post.setTags(tags);
        return post;
    }
}
