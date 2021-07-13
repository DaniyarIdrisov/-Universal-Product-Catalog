package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Category;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Order;

import java.util.List;

public interface ItemsRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByCategory(Category category);

    List<Item> findAllByOrdersContains(Order order);

    List<Item> findAllByLabelContains(String query);

    List<Item> findAllByLabelContainsAndAndCategory_Id(String query, Long categoryId);
}
