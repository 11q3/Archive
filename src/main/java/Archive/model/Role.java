package Archive.model;

import jakarta.persistence.*;

@Entity
@Table(name="role")
public class Role {
    public Role(String name) {
        this.name = name;
    }

    public Role() {}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
