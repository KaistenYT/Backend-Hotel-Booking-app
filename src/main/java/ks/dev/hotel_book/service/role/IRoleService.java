package ks.dev.hotel_book.service.role;

import java.util.List;

import ks.dev.hotel_book.model.Role;
import ks.dev.hotel_book.model.User;

public interface IRoleService {

    List<Role> getRoles();
    Role createRole(Role theRole);

    void deleteRole(Long id);
    Role findByName(String name);

    User removeUserFromRole(Long userId, Long roleId);
    User assignRoleToUser(Long userId, Long roleId);
    Role removeAllUsersFromRole(Long roleId);

}
