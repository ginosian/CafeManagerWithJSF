package springContext.dto;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;

/**
 * Created by Martha on 7/29/2016.
 */
@ManagedBean(name = "role")
@Entity
@Table(name = "roles")
public class RoleDTO {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private String role;

    public RoleDTO() {
    }

    public RoleDTO(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
