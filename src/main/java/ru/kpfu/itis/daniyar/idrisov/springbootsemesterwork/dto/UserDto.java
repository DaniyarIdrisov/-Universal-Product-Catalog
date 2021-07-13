package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.User;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private Long id;

    private Long githubId;

    private String login;

    private String hashPassword;

    private String firstName;

    private String lastName;

    private String email;

    private String confirmCode;

    private User.State state;

    private User.EmailState emailState;

    private User.Role role;

    public static UserDto from(User user) {
        if (user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .githubId(user.getGithubId())
                .login(user.getLogin())
                .hashPassword(user.getHashPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .confirmCode(user.getConfirmCode())
                .state(user.getState())
                .emailState(user.getEmailState())
                .role(user.getRole())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

}
