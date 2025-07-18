package io.github.masashi2.bbs.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.masashi2.bbs.model.Post;
import io.github.masashi2.bbs.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    //投稿の保存
    public Post savePost(String title, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        return postRepository.save(post);
    }

    //投稿の取得
    public Optional<Post> getPostById(Long postid) {
        return postRepository.findById(postid);
    }

    //投稿の全取得
    public Iterable<Post> getAllPosts() {
        return postRepository.findAll();
    }

    
}
