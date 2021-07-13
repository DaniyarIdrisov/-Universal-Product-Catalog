package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.CommentDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserEditProfileForm;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserRegistrationForm;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.Comment;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models.User;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.CommentsRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.repositories.UsersRepository;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.security.details.UserDetailsImpl;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.utils.EmailUtil;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.utils.MailsGenerator;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto.from;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Autowired
    private MailsGenerator mailsGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${server.url}")
    private String serverUrl;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.subject}")
    private String subject;

    @Override
    public void addUser(UserRegistrationForm form) {
        User user = User.builder()
                .login(form.getLogin())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .confirmCode(UUID.randomUUID().toString())
                .state(User.State.ACTIVE)
                .emailState(User.EmailState.NOT_CONFIRMED)
                .role(User.Role.USER)
                .build();
        usersRepository.save(user);
        String confirmMail = mailsGenerator.getMailForConfirm(serverUrl, user.getConfirmCode());
        emailUtil.sendMail(user.getEmail(), subject, from, confirmMail);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return from(usersRepository.findUserByLogin(login).orElse(null));
    }

    @Override
    public UserDto getUserByConfirmCode(String confirmCode) {
        return from(usersRepository.findUserByConfirmCode(confirmCode).orElse(null));
    }

    @Override
    public void updateConfirmCode(Long id) {
        usersRepository.updateEmailState(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return from(usersRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long userId) {
        return from(usersRepository.findById(userId).orElse(null));
    }

    @Override
    public void updateItem(Long userId, String role, String state, String emailState) {
        User user = usersRepository.findById(userId).orElse(null);
        if (role != null && !role.equals("Default")) {
            if (role.equals("ADMIN")) {
                user.setRole(User.Role.ADMIN);
            }
            else {
                user.setRole(User.Role.USER);
            }
        }
        if (state != null && !state.equals("Default")) {
            if (state.equals("ACTIVE")) {
                user.setState(User.State.ACTIVE);
            }
            else {
                user.setState(User.State.BANNED);
            }
        }
        if (emailState != null && !emailState.equals("Default")) {
            if (emailState.equals("CONFIRMED")) {
                user.setEmailState(User.EmailState.CONFIRMED);
            }
            else {
                user.setEmailState(User.EmailState.NOT_CONFIRMED);
            }
        }
        usersRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        usersRepository.deleteById(userId);
    }

    @Override
    public void editProfile(UserEditProfileForm form, Long userId) {
        User user = usersRepository.findById(userId).orElse(null);
        if (!form.getFirstName().equals("") && form.getFirstName() != null) {
            user.setFirstName(form.getFirstName());
        }
        if (!form.getLastName().equals("") && form.getLastName() != null) {
            user.setLastName(form.getLastName());
        }
        if (!form.getPassword().equals("") && form.getPassword() != null) {
            user.setHashPassword(passwordEncoder.encode(form.getPassword()));
        }
        usersRepository.save(user);
    }

    @Override
    public UserDto getUserByComment(CommentDto commentDto) {
        Comment comment = commentsRepository.findById(commentDto.getId()).orElse(null);
        return from(usersRepository.findUserByCommentsContains(comment).orElse(null));
    }

    @Override
    public boolean hasChildren(Long userId) {
        User user = usersRepository.findById(userId).orElse(null);
        assert user != null;
        if (!user.getComments().isEmpty() || !user.getOrders().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Integer getMaxCountOfComments() {
        return usersRepository.findMaxCountOfComments();
    }

    @Override
    public Integer getMaxCountOfOrders() {
        return usersRepository.findMaxCountOfOrders();
    }

    @Override
    public String getUsernamePrincipal(UserDetailsImpl userDetails) {
        String username;
        if (userDetails == null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            username = (String) authentication.getDetails();
        }
        else {
            username = userDetails.getUsername();
        }
        return username;
    }

}
