package test.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.chat.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
}
