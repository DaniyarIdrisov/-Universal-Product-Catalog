package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.OrdersService;

import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/showOrderItems/{order_id}")
    public String showOrderItems(@PathVariable("order_id") Long orderId, Model model) {
        OrderDto orderDto = ordersService.getOrderById(orderId);
        model.addAttribute("order", orderDto);
        model.addAttribute("items", orderDto.getItems());
        return "show_order_items";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/ordersPage")
    public String ordersPage(Model model) {
        List<OrderDto> orderDtoList = ordersService.getAll();
        model.addAttribute("orders", orderDtoList);
        return "orders";
    }

}
