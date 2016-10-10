package springContext.dto;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

/**
 * Created by Martha on 7/29/2016.
 */
@ManagedBean(name = "user")
@Entity
@Table(name = "users")
public class UserDTO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RoleDTO role;

    public UserDTO() {
    }

    public UserDTO(String username, String password, RoleDTO role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
