package ru.job4j.models;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import javax.persistence.*;
import java.util.List;

@Entity
public class AdUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phone;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "adUser")
    List<Ad> ad;

    public AdUser() {
    }

    public AdUser(String name) {
        this.name = name;
    }

    public AdUser(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
