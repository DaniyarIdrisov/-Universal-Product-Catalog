package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.ItemsRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.ItemsService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.OrdersService;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class ProfileController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private OrdersService ordersService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profilePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = usersService.getUsernamePrincipal(userDetails);
        UserDto userDto = usersService.getUserByLogin(username);
        List<OrderDto> orders = ordersService.getOrdersByUserId(userDto.getId());
        model.addAttribute("user", userDto);
        model.addAttribute("orders", orders);
        model.addAttribute("role", userDto.getRole());
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/editProfilePage")
    public String getEditProfilePage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = usersService.getUsernamePrincipal(userDetails);
        UserDto userDto = usersService.getUserByLogin(username);
        model.addAttribute("userEditProfileForm", new UserEditProfileForm());
        model.addAttribute("user", userDto);
        return "edit_profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/editProfile")
    public String editProfile(@Valid UserEditProfileForm form, BindingResult bindingResult, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam(value = "id", required = false) Long userId) {
        String username = usersService.getUsernamePrincipal(userDetails);
        if (!form.getPassword().equals("") && form.getPassword() != null) {
            if (bindingResult.hasErrors()) {
                bindingResult.getAllErrors().stream().anyMatch(errors -> {
                    for (String error : Objects.requireNonNull(errors.getCodes())) {
                        if (error.equals("userEditProfileForm.ValidRepeatPasswords")) {
                            model.addAttribute("passwordsErrorMessage", errors.getDefaultMessage());
                        }
                    }
                    return true;
                });
                UserDto userDto = usersService.getUserByLogin(username);
                model.addAttribute("user", userDto);
                return "edit_profile";
            }
            usersService.editProfile(form, userId);
            return "redirect:/editProfilePage";
        }
        else {
            usersService.editProfile(form, userId);
            return "redirect:/editProfilePage";
        }
    }

}
