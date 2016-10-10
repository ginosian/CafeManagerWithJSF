package springContext.dao;

import springContext.dto.RoleDTO;
import springContext.dto.UserDTO;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface UserDAO {

    /** Creates new user.*/
    UserDTO addUser(UserDTO user);

    /** Finds specified user by Id*/
    UserDTO getUserById(Long userId);

    /** Finds specified user by username*/
    UserDTO getUserByUsername(String userName);

    /** Get users list with specified role.*/
    List<UserDTO> getAllUsersByRole(String role);

    /** Creates new role.*/
    RoleDTO addRole(RoleDTO role);

    /** Finds specified role*/
    RoleDTO getRole(String role);

}
