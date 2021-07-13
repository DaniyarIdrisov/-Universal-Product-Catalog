package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.UsersService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'GOD')")
    @GetMapping("/usersPage")
    public String getUsersPage(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<UserDto> userDtoList = usersService.getAllUsers();
        UserDto userDto = usersService.getUserByLogin(userDetails.getUsername());
        model.addAttribute("users", userDtoList);
        model.addAttribute("thisUser", userDto);
        return "users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'GOD')")
    @GetMapping("/editUserPage/{user_id}")
    public String getUserPage(@PathVariable("user_id") Long userId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        UserDto userDto = usersService.getUserById(userId);
        UserDto thisUserDto = usersService.getUserByLogin(userDetails.getUsername());
        model.addAttribute("user", userDto);
        model.addAttribute("thisUser", thisUserDto);
        return "edit_users";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'GOD')")
    @PostMapping("/editUser/{user_id}")
    public String editUser(@PathVariable("user_id") Long userId, @RequestParam(value = "role", required = false) String role, @RequestParam(value = "state", required = false) String state, @RequestParam(value = "email_state", required = false) String emailState) {
        usersService.updateItem(userId, role, state, emailState);
        return "redirect:/editUserPage/{user_id}";
    }

    @PreAuthorize("hasAuthority('GOD')")
    @PostMapping("/deleteUser/{user_id}")
    public String deleteUser(@PathVariable("user_id") Long userId) {
        if (usersService.hasChildren(userId)) {
            return "redirect:/deleteFail";
        }
        usersService.deleteUser(userId);
        return "redirect:/usersPage";
    }

}
