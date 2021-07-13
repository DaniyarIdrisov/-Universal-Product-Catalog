package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;

import java.util.List;

public interface OrdersService {

    void addOrder(OrderDto orderDto, String username, List<ItemDto> itemsDto);

    List<OrderDto> getOrdersByUserId(Long id);

    OrderDto getOrderById(Long orderId);

    List<OrderDto> getAll();

}
