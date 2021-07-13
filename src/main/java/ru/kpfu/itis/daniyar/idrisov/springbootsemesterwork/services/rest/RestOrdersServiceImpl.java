package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.ItemsRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.OrdersRepository;

import java.util.List;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto.fromModel;

@Service
public class RestOrdersServiceImpl implements RestOrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ItemsRepository itemsRepository;

    @Override
    public List<OrderDto> getOrdersByItemId(Long itemId) {
        Item item = itemsRepository.findById(itemId).orElseThrow(IllegalArgumentException::new);
        return fromModel(ordersRepository.findAllByItemsContains(item));
    }


}
