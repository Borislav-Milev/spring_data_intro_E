package bookshop_system.app.entity.ex_2;

import bookshop_system.app.entity.BaseEntity;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Set;

import static bookshop_system.app.constants.Message.*;

@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Length(max = 20)
    private String firstName;

    @Length(max = 20)
    private String lastName;

    @NonNull
    @Length(min = 4, max = 30, message = INCORRECT_USERNAME_LENGTH)
    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @NonNull
    @Length(min = 6, max = 50, message = INCORRECT_PASSWORD_LENGTH)
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,50}$",
            message = INCORRECT_PASSWORD_INPUT)
    @Column(nullable = false)
    private String password;

    @NonNull
    @Column(nullable = false)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    private LocalDateTime registeredOn;

    private LocalDateTime lastTimeLoggedIn;

    @Size(min = 0, max = 120, message = INVALID_AGE_INPUT)
    private Integer age;

    private Boolean isDeleted;

    @ManyToOne
    @ToString.Exclude
    private Town bornInTown;

    @ManyToOne
    @ToString.Exclude
    private Town livingInTown;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<User> userFriends;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Album> albums;



    public String fullName(){
        return this.firstName + " " + this.lastName;
    }
}
