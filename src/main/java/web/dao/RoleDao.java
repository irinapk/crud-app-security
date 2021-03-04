package web.dao;

import web.model.Role;
import web.model.User;

import java.util.Set;

public interface RoleDao {

    public void assignRole (User user, Role role);

    public void saveRole (Role role);

    public void deleteRole (int id);

    public Set<Role> showAllRoles ();

    public Role findRoleByName (String name);

    Role findRoleById(int id);
}
