package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.User;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.UsersRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;

@Component
public class GitHubOauthAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        GitHubOauthAuthentication gitHubOauthAuthentication = (GitHubOauthAuthentication) authentication;
        User user = usersRepository.findUserByGithubId(gitHubOauthAuthentication.getGithubId()).orElse(null);
        gitHubOauthAuthentication.setUserDetails(new UserDetailsImpl(user));
        gitHubOauthAuthentication.setAuthenticated(true);
        return gitHubOauthAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GitHubOauthAuthentication.class.equals(aClass);
    }

}
