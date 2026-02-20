package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> posts = new ArrayList<>();
    private final CategoryService categoryService;

    public PostServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<Post> getAll() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .toList();
    }

    @Override
    public List<Post> getAllByCategoryId(UUID categoryId) {
        return posts.stream()
                .filter(p -> p.getCategory() != null && p.getCategory().getId().equals(categoryId))
                .sorted(Comparator.comparing(Post::getCreatedDate).reversed())
                .toList();
    }

    @Override
    public Post getById(UUID id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryService.getById(categoryId);
        Post post = new Post(title, content, category);
        posts.add(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content) {
        Post post = getById(id);
        if (post != null) {
            post.setTitle(title);
            post.setContent(content);
        }
        return post;
    }

    @Override
    public boolean deleteById(UUID id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }
}
