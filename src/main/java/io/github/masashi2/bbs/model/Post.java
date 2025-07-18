//投稿のID、タイトル、内容を保持
package io.github.masashi2.bbs.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity//DBにマッピングするクラス
public class Post {
    @Id//主キーであるidにのみアノテーションを付ける
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主キーの自動生成
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @JoinColumn(name = "parent_id")
    private Post parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Post> replies = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Long getPostId() { return postId; }
    public void setPostid(Long postId) { this.postId = postId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Post getParent() { return parent; }
    public void setParent(Post parent) { this.parent = parent; }

    public List<Post> getReplies() { return replies; }
    public void setReplies(List<Post> replies) { this.replies = replies; }
}
