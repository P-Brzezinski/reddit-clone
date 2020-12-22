package pl.brzezinski.redditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.brzezinski.redditclone.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
