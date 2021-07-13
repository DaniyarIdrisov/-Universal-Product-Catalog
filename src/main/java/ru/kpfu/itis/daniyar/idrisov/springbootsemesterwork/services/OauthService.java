package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;

public interface OauthService {

    UserDto getUserByCode(String code);

}
