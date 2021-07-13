package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginForm {

    private String login;

    private String password;

}
