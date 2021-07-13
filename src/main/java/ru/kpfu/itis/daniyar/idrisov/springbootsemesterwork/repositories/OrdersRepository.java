package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Order;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserId(Long id);

    List<Order> findAllByItemsContains(Item item);

}
