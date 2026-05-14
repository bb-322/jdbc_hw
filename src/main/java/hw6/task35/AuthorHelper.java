package hw6.task35;

import hw6.task35.entity.Author;

import jakarta.persistence.criteria.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorHelper {

    private SessionFactory sf;

    public AuthorHelper() {
        sf = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList(){
        Session session = sf.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);
        cq.select(root);
        List<Author> authorList = session.createQuery(cq).getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session s = sf.openSession();
        return s.find(Author.class, id);
    }

    public void addAuthor(Author author){
        try (Session s = sf.openSession()) {
            s.beginTransaction();

            s.persist(author);

            s.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
