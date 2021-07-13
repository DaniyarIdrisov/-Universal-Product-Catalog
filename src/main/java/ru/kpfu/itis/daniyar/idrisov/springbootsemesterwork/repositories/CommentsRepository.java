package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Comment;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;

import java.util.List;
import java.util.Map;

public interface CommentsRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByItem_Id(Long id);

    List<Comment> findAllByItemContains(Item item);

}
