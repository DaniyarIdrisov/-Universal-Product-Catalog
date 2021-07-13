package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.models;

import lombok.*;
import ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 7L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @Column(name = "github_id")
    private Long githubId;

    @Column(name = "hash_password")
    private String hashPassword;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "confirm_code")
    private String confirmCode;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State {
        ACTIVE, BANNED
    }

    @Column(name = "email_state")

    private EmailState emailState;

    public enum EmailState {
        CONFIRMED, NOT_CONFIRMED
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        USER, ADMIN, GOD
    }

    @Transient
    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    @Transient
    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    @Transient
    public boolean isConfirmed() {
        return this.emailState == EmailState.CONFIRMED;
    }

    @Transient
    public  boolean isNotConfirmed() {
        return this.emailState == EmailState.NOT_CONFIRMED;
    }

    @Transient
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    @Transient
    public boolean isGod() {
        return this.role == Role.GOD;
    }

    @Transient
    public boolean isUser() {
        return this.role == Role.USER;
    }

}
