package Archive.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long roleId;

    private String authority;

    private String name;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long id) {
        this.roleId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Long roleId) {
        this.name = name;
        this.roleId = roleId;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public String setAuthority(String authority) {
        return this.authority = authority;
    }
}
