package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public void assignRole(User user, Role role) {
        roleDao.assignRole(user, role);
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Transactional
    @Override
    public void deleteRole(int id) {
        roleDao.deleteRole(id);
    }

    @Override
    public Set<Role> showAllRoles() {
        return roleDao.showAllRoles();
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    public Role findRoleById(int id) {
        return roleDao.findRoleById(id);
    }
}
