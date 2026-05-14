package hw6.task2;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.*;

import java.util.List;

public class UserHelper {

    private SessionFactory sf;

    public UserHelper() {
        sf = HibernateUtil.getSessionFactory();
    }

    public void updateUser(long id, User user) {
        Session s = sf.openSession();

        s.beginTransaction();

        User u = s.find(User.class, id);

        if (user.getName() != null) { u.setName(user.getName()); }
        if (user.getLastname() != null) { u.setLastname(user.getLastname()); }
        if (user.getAge() != null) { u.setAge(user.getAge()); }

        s.getTransaction().commit();
        s.close();
    }

    public List<User> getUsers() {
        return getUsers(null);
    }

    public List<User> getUsers(Integer lastnameLength) {
        Session s = sf.openSession();

        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        cq.select(root);

        if (lastnameLength != null) {
            Predicate p = cb.gt(cb.length(root.get("lastname")), lastnameLength);
            cq.where(p);
        }
        return s.createQuery(cq).getResultList();
    }
}
