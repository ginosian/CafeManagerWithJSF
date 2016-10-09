package managedbeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.WebApplicationContextUtils;
import springContext.dto.UserDTO;
import springContext.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Created by Martha on 7/29/2016.
 */
@ManagedBean(name = "login")
public class AuthController implements Serializable {
    @Autowired
    Environment environment;
    @Autowired
    UserService userService;

    @PostConstruct
    private void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getContext();
        WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext).
                getAutowireCapableBeanFactory().
                autowireBean(this);
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        init();
    }

    String username;
    String password;

    public AuthController() {
    }

    public String login() throws Exception {
        userService.initDefaultUsers();
        if(userService.getUserByUsername(username) != null ) {
            UserDTO user = userService.getUserByUsername(username);
            final String role = user.getRole().getRole();
            final String waiter = environment.getProperty("role_waiter");
            final String manager = environment.getProperty("role_manager");

            if (role.equals(waiter)) return "waiter.xhtml?faces-redirect=true";
            if (role.equals(manager)) return "waiter.xhtml?faces-redirect=true";
            else return "waiter.xhtml?faces-redirect=true";
        }
        return "waiter.xhtml?faces-redirect=true";
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
}
