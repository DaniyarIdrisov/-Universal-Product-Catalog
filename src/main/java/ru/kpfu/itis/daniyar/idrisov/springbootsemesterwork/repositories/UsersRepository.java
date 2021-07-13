package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Comment;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByConfirmCode(String confirmCode);

    Optional<User> findUserByCommentsContains(Comment comment);

    Optional<User> findUserByGithubId(Long id);

    @Modifying
    @Query("UPDATE User SET emailState = 'CONFIRMED' WHERE id = :id")
    void updateEmailState(Long id);

    @Query(value = "SELECT max(o) from (SELECT u.id, count(u.id) as o from comment LEFT JOIN users u on u.id = comment.user_id group by u.id) as t", nativeQuery = true)
    Integer findMaxCountOfComments();

    @Query(value = "SELECT max(o) from (SELECT u.id, count(u.id) as o from orders LEFT JOIN users u on u.id = orders.user_id group by u.id) as t", nativeQuery = true)
    Integer findMaxCountOfOrders();

}
