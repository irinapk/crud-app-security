package web.dao;

import org.springframework.stereotype.Component;
import web.config.AppConfig;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private AppConfig ac = new AppConfig();

    // @PersistenceContext(unitName = "crud-app_user")

    @Override
    public void saveUser(String name, String lastName) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        User user = new User(name, lastName);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void saveUser(User user) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeUserById(int id) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        User user = em.find(User.class, id);
        em.getTransaction().begin();
        em.remove(user);
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
        User userToBeUpdated = em.find(User.class, id);
        em.getTransaction().begin();
        userToBeUpdated.setName(newUser.getName());
        userToBeUpdated.setLastName(newUser.getLastName());
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public User show(int id) {
        EntityManager em = ac.entityManagerFactory().getObject().createEntityManager();
        return em.find(User.class, id);
    }


}
