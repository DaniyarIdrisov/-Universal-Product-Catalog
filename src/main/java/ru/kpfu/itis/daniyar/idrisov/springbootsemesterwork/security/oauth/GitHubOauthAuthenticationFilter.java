package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.oauth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GitHubOauthAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = httpServletRequest.getCookies();
        String cookieName = "git_id";
        Cookie cookie = null;
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        if (cookie != null) {
            Long gitHubId = Long.parseLong(cookie.getValue());
            GitHubOauthAuthentication gitHubOauthAuthentication = new GitHubOauthAuthentication(gitHubId);
            SecurityContextHolder.getContext().setAuthentication(gitHubOauthAuthentication);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
