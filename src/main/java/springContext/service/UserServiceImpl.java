package springContext.service;

import springContext.dao.UserDAO;
import springContext.dto.RoleDTO;
import springContext.dto.UserDTO;
import springContext.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    UserDAO userDAO;

    @Autowired
    Environment environment;

    private static boolean DEFAULTS_ARE_SET = false;

    @Override
    public UserDTO createUser(String username, String password, String role) throws Exception {
        if(username.isEmpty() || password.isEmpty() || role.isEmpty())throw new NullOrEmptyArgumentsException();

        RoleDTO roleDTO = userDAO.getRole(role);
        if(roleDTO == null)throw new NoSuchRoleException();

        if(userDAO.getUserByUsername(username) != null) throw new UserExistException();

        UserDTO userDTO = new UserDTO(username, password, roleDTO);

        return userDAO.addUser(userDTO);
    }

    @Override
    public UserDTO getUserById(String id) throws Exception {
        if(id == null || id.isEmpty()) throw new NullOrEmptyArgumentsException();

        UserDTO userDTO = userDAO.getUserById(Long.parseLong(id));
        if(userDTO == null)throw new NoSuchUserException();

        return userDTO;
    }

    @Override
    public UserDTO getUserByUsername(String username) throws Exception {
        if(username == null || username.isEmpty()) throw new NullOrEmptyArgumentsException();

        UserDTO userDTO = userDAO.getUserByUsername(username);
        if(userDTO == null)throw new NoSuchUserException();

        return userDTO;
    }

    @Override
    public List<UserDTO> getAllUsersByRole(String role) throws Exception{
        if(role == null || role.isEmpty())throw new NullOrEmptyArgumentsException();

        if(userDAO.getRole(role) == null) throw new NoSuchRoleException();

        List<UserDTO> users = userDAO.getAllUsersByRole(role);
        if(users.size() == 0) throw new NoSuchUserException();

        return userDAO.getAllUsersByRole(role);
    }

    @Override
    public RoleDTO createRole(String role) throws Exception {
        if(role == null || role.isEmpty()) throw new NullOrEmptyArgumentsException();

        if(userDAO.getRole(role) != null) throw new RoleExistException();

        RoleDTO roleDTO = new RoleDTO(role);

        return userDAO.addRole(roleDTO);
    }

    @Override
    public RoleDTO getRole(String role) throws Exception{
        if(role == null || role.isEmpty()) throw new NullOrEmptyArgumentsException();

        RoleDTO roleDTO = userDAO.getRole(role);
        if(roleDTO == null)throw new NoSuchRoleException();

        return roleDTO;
    }

    @Override
    public void initDefaultUsers() {
        if(DEFAULTS_ARE_SET) return;

        userDAO.addRememberMeTable();

        String manager_username = environment.getProperty("manager_name");
        String manager_password = environment.getProperty("manager_password");
        String waiter_username = environment.getProperty("waiter_username");
        String waiter_password = environment.getProperty("waiter_password");
        String role_manager = environment.getProperty("role_manager");
        String role_waiter = environment.getProperty("role_waiter");

        RoleDTO managersRole = new RoleDTO(role_manager);
        RoleDTO waitersRole = new RoleDTO(role_waiter);
        RoleDTO manager_role = userDAO.getRole(role_manager);
        RoleDTO waiter_role = userDAO.getRole(role_waiter);

        if (manager_role == null){
            userDAO.addRole(managersRole);
        }

        if (waiter_role == null){
            userDAO.addRole(waitersRole);
        }

        try {
            getUserByUsername(manager_username);
        } catch (Exception e) {
            try {
               createUser(manager_username, manager_password, role_manager);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        try {
            getUserByUsername(waiter_username);
        } catch (Exception e) {
            try {
                createUser(waiter_username, waiter_password, role_waiter);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        DEFAULTS_ARE_SET = true;
    }
}
