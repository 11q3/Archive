package Archive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Size(min = 2, max = 16, message = "Неверное имя пользователя. (2-16 символов)")
    @Column(name = "first_name")
    private String firstName;

    @Size(min = 2, max = 16, message = "Неверное имя пользователя. (2-16 символов)")
    @Column(name = "last_name")
    private String lastName;

    @Column(nullable=false, unique=true)
    private String email;

    @Column(nullable=false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "USER_ID", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "role_id"))

    private List<Role> roles = new ArrayList<>();

    private String profilePicture;

}
