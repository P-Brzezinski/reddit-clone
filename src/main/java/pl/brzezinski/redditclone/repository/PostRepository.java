package pl.brzezinski.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brzezinski.redditclone.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
