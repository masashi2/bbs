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
    private long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "parentid")
    private Post parentId;

    @OneToMany(mappedBy = "parentId", cascade = CascadeType.ALL)
    private List<Post> replies = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public long getPostId() { return postId; }
    public void setPostid(long postId) { this.postId = postId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Post getParentId() { return parentId; }
    public void setParentId(Post parentId) { this.parentId = parentId; }

    public List<Post> getReplies() { return replies; }
    public void setReplies(List<Post> replies) { this.replies = replies; }
}
