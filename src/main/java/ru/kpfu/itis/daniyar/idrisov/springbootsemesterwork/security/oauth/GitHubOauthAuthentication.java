package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.oauth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;

import java.util.Collection;

public class GitHubOauthAuthentication implements Authentication {

    private UserDetails userDetails;

    private Long githubId;

    private boolean authenticated;

    public GitHubOauthAuthentication(Long githubId) {
        this.githubId = githubId;
        this.authenticated = false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return userDetails;
    }

    @Override
    public String getDetails() {
        return userDetails.getUsername();
    }

    @Override
    public UserDetails getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        authenticated = b;
    }

    @Override
    public String getName() {
        return githubId.toString();
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public Long getGithubId() {
        return githubId;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = (UserDetailsImpl) userDetails;
    }

    public void setGithubId(Long githubId) {
        this.githubId = githubId;
    }

}
