package ru.fixedfox.musicservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fixedfox.musicservice.entity.User;

import java.security.Provider;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByUsername(String username);

    Optional<User> findByTelegramNickname(String telegramNickname);

    @Query(nativeQuery = true, value = "select t1.id, t1.username, t1.password, t1.name, t1.telegram_nickname, t1.telegram_id from users t1 " +
            "JOIN subscriptions t2 ON t2.user_id = t1.id " +
            "JOIN creators t3 ON t3.id = t2.creator_id " +
            "JOIN creators_tracklists t4 ON t4.creator_id = t3.id " +
            "JOIN tracklists t5 ON t5.id = t4.tracklist_id " +
            "WHERE t1.telegram_id IS NOT NULL AND t5.id = :userId")
    Set<User> findUserBySubscriptionByTracklist(Long userId);
}
