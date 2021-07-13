package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserLoginForm;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.oauth.GitHubOauthAuthentication;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services.OauthService;

import javax.annotation.security.PermitAll;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Value("${oauth.client.id}")
    private String oauthClientId;

    @Autowired
    private OauthService oauthService;

    @PermitAll
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("userLoginForm", new UserLoginForm());
        model.addAttribute("oauthClientId", oauthClientId);
        return "login";
    }

    @PermitAll
    @GetMapping("/login/oauth2/code/github")
    public String getOauthCode(@RequestParam String code, HttpServletResponse response) {
        UserDto userDto = oauthService.getUserByCode(code);
        String gitId = userDto.getGithubId().toString();
        Cookie cookie = new Cookie("git_id", gitId);
        response.addCookie(cookie);
        return "redirect:/profile";
    }

}
