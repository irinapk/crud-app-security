package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.config.AppConfig;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private AppConfig ac;

    @Override
    public void saveUser(User user) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        em.getTransaction().begin();
        user.setPassword("default");
        Set<Role> set = new HashSet<>();
        set.add(em.find(Role.class, 2));
        user.setRoles(set);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeUserById(int id) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public void update(int id, User newUser) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        em.getTransaction().begin();
        em.detach(em.find(User.class, id));
        em.merge(newUser);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User show(int id) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        return em.find(User.class, id);
    }

    public User getUserByName (String username) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        em.getTransaction().begin();
        User user = em.createQuery(
                "SELECT u from User u WHERE u.name = :username", User.class).
                setParameter("username", username).getSingleResult();
        em.getTransaction().commit();
        em.close();
        return user;
    }
}
