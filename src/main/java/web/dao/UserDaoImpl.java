package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.config.AppConfig;
import web.model.User;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private AppConfig ac;

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
}
