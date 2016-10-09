package springContext.service;

import springContext.dto.RoleDTO;
import springContext.dto.UserDTO;
import springContext.exception.*;

import java.util.List;

/**
 * Created by Martha on 7/29/2016.
 */
public interface UserService {

    /** Creates new user.
     * @param username username and also a logging credential.
     * @param password Password
     * @param role String representation of role, it is recommended to take values from
     *             classpath:config.properties file.
     * @return {@link UserDTO}
     * @throws UserExistException if user exist.
     * @throws NoSuchRoleException if role doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    UserDTO createUser(String username, String password, String role) throws Exception;

    /** Finds user by Id.
     * @param id id of searching user.
     * @return {@link UserDTO}
     * @throws NoSuchUserException if user doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    UserDTO getUserById(String id) throws Exception;

    /** Finds user by username.
     * @param username username and also a logging credential.
     * @return {@link UserDTO}
     * @throws NoSuchUserException if user doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    UserDTO getUserByUsername(String username) throws Exception;


    /** Get users list with specified role. @param username username and also a logging credential.
     * @param role String representation of role, it is recommended to take values from
     *             classpath:config.properties file.
     * @return {@link List<UserDTO>}
     * @throws NoSuchRoleException if role doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    List<UserDTO> getAllUsersByRole(String role) throws Exception;

    /** Creates new role.
     * @param role String representation of role, it is recommended to take values from
     *             classpath:config.properties file
     * @return {@link RoleDTO}
     * @throws RoleExistException if role does exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    RoleDTO createRole(String role) throws Exception;

    /** Finds specified role.
     * @param role String representation of role, it is recommended to take values from
     *             classpath:config.properties file
     * @return {@link RoleDTO}
     * @throws NoSuchRoleException if role doesn't exist.
     * @throws NullOrEmptyArgumentsException if any argument is missing or is a null.
     * */
    RoleDTO getRole(String role) throws Exception;

    /** Checks if default waiter and manager doesn't exist creates one from each.
     * */
    void initDefaultUsers();

}
