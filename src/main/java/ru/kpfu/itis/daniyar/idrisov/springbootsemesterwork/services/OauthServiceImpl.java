package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import lombok.SneakyThrows;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.User;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.UsersRepository;

import java.util.Objects;
import java.util.UUID;

@Service
public class OauthServiceImpl implements OauthService {

    @Value("${oauth.client.id}")
    private String oauthClientId;

    @Value("${oauth.client.secrets}")
    private String oauthClientSecrets;

    @Value("${oauth.github.user.api}")
    private String oauthGitHubUserApi;

    @Value("${oauth.github.uri.access.token}")
    private String oauthGitHubUriAccessToken;

    @Autowired
    private UsersRepository usersRepository;

    @SneakyThrows
    @Override
    public UserDto getUserByCode(String code) {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("client_id", oauthClientId)
                .add("client_secret", oauthClientSecrets)
                .add("code", code)
                .build();
        Request request = new Request.Builder()
                .url(oauthGitHubUriAccessToken)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        ResponseBody responseBody = call.execute().body();
        String[] strings = Objects.requireNonNull(responseBody).string().split("=");
        strings = strings[1].split("&");
        String accessToken = strings[0];
        request = new Request.Builder()
                .addHeader("Authorization", "token " + accessToken)
                .url(oauthGitHubUserApi)
                .build();
        call = okHttpClient.newCall(request);
        responseBody = call.execute().body();
        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(responseBody).string());
        String email = jsonObject.get("email").toString();
        Long githubId = jsonObject.getLong("id");
        String firstName = jsonObject.getString("login");
        String lastName = jsonObject.getString("name");
        User user = usersRepository.findUserByGithubId(githubId).orElse(null);
        if (user == null) {
            User newUser = User.builder()
                    .githubId(githubId)
                    .login("githubUser" + UUID.randomUUID())
                    .firstName(firstName)
                    .lastName(lastName)
                    .hashPassword(" ")
                    .confirmCode(UUID.randomUUID().toString())
                    .emailState(User.EmailState.NOT_CONFIRMED)
                    .role(User.Role.USER)
                    .state(User.State.ACTIVE)
                    .build();
            if (email.equals("null")) {
                newUser.setEmail("no email");
            }
            else {
                newUser.setEmail(email);
            }
            usersRepository.save(newUser);
            return UserDto.from(newUser);
        } else {
            return UserDto.from(user);
        }
    }

}
