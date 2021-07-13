package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Order;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private Long id;

    private Double sumPrice;

    private String orderTime;

    private String address;

    private List<ItemDto> items;

    private String username;

    public static OrderDto fromModel(Order order) {
        if (order == null) {
            return null;
        }
        return OrderDto.builder()
                .id(order.getId())
                .items(ItemDto.fromModel(order.getItems()))
                .sumPrice(order.getSumPrice())
                .orderTime(order.getOrderTime())
                .address(order.getAddress())
                .username(order.getUser().getLogin())
                .build();
    }

    public static List<OrderDto> fromModel(List<Order> users) {
        return users.stream()
                .map(OrderDto::fromModel)
                .collect(Collectors.toList());
    }

}
