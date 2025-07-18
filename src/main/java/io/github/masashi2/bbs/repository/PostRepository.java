//CRUD操作を自動で提供
package io.github.masashi2.bbs.repository;

import io.github.masashi2.bbs.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
