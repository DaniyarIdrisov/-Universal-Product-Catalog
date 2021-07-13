package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto;

import java.util.List;

public interface RestOrdersService {

    List<OrderDto> getOrdersByItemId(Long itemId);

}
