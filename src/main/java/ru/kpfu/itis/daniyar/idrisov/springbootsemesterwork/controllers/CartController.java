package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.ItemDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.OrderDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.OrdersService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrdersService ordersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/orderCompleted")
    public String orderCompletedPage() {
        return "order_completed";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/makeOrder")
    public String makeOrder(HttpServletRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails, OrderDto orderDto) {
        String username = usersService.getUsernamePrincipal(userDetails);
        if (orderDto.getAddress().equals("")) {
            return "redirect:/cart";
        }
        HttpSession session = request.getSession();
        Date date = new Date();
        List<ItemDto> items = (List<ItemDto>) session.getAttribute("items");
        OrderDto order = OrderDto.builder()
                .sumPrice((Double) session.getAttribute("sum"))
                .address(orderDto.getAddress())
                .orderTime(date.toString())
                .build();
        session.removeAttribute("items");
        ordersService.addOrder(order, username, items);
        return "redirect:/orderCompleted";
    }

    @PermitAll
    @GetMapping("/cart")
    public String cartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ItemDto> items = (List<ItemDto>) session.getAttribute("items");
        double sum = 0;
        if (!(items == null)) {
            for (ItemDto item : items) {
                sum = sum + item.getPrice();
            }
        }
        session.setAttribute("sum", sum);
        model.addAttribute("sum", sum);
        model.addAttribute("items", items);
        return "cart";
    }

    @PermitAll
    @PostMapping("/removeFromCart")
    public String removeFromCart(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<ItemDto> items = (List<ItemDto>) session.getAttribute("items");
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(items.get(i));
                break;
            }
        }
        session.setAttribute("items", items);
        return "redirect:/cart";
    }

}
