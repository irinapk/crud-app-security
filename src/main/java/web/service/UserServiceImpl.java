package web.service;

import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();

    @Override
    public void saveUser(String name, String lastName) {
        userDao.saveUser(name, lastName);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void removeUserById(int id) {
        userDao.removeUserById(id);
    }

    @Override
    public void update(int id, User newUser) {
        userDao.update(id, newUser);
    }

    @Override
    public User show(int id) {
        return userDao.show(id);
    }
}
