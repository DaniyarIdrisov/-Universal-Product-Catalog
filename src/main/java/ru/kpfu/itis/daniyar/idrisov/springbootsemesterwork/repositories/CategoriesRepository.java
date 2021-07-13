package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Category;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByItemsContains(Item item);

}
