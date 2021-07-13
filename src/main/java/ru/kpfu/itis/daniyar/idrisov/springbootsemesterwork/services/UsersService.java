package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserEditProfileForm;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserRegistrationForm;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;

import java.util.List;

public interface UsersService {

    void addUser(UserRegistrationForm form);

    UserDto getUserByLogin(String login);

    UserDto getUserByConfirmCode(String confirmCode);

    void updateConfirmCode(Long id);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long userId);

    void updateItem(Long userId, String role, String state, String emailState);

    void deleteUser(Long userId);

    void editProfile(UserEditProfileForm form, Long userId);

    UserDto getUserByComment(CommentDto commentDto);

    boolean hasChildren(Long userId);

    Integer getMaxCountOfComments();

    Integer getMaxCountOfOrders();

    String getUsernamePrincipal(UserDetailsImpl userDetails);

}
