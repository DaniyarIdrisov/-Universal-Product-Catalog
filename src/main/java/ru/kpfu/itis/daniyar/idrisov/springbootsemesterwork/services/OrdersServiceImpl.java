package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Item;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Order;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.User;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.OrdersRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.UsersRepository;

import java.util.List;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto.fromModel;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void addOrder(OrderDto orderDto, String username, List<ItemDto> itemsDto) {
        User user = usersRepository.findUserByLogin(username).orElse(null);
        Order order = Order.builder()
                .id(orderDto.getId())
                .sumPrice(orderDto.getSumPrice())
                .orderTime(orderDto.getOrderTime())
                .address(orderDto.getAddress())
                .items(Item.fromDto(itemsDto))
                .user(user)
                .build();
        ordersRepository.save(order);
    }

    @Override
    public List<OrderDto> getOrdersByUserId(Long id) {
        return fromModel(ordersRepository.findAllByUserId(id));
    }

    @Override
    public OrderDto getOrderById(Long orderId) {
        return fromModel(ordersRepository.findById(orderId).orElse(null));
    }

    @Override
    public List<OrderDto> getAll() {
        return fromModel(ordersRepository.findAll());
    }

}
