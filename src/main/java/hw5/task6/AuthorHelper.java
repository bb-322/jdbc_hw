package hw5.task6;

import jakarta.persistence.criteria.*;
import org.hibernate.*;

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

    public void addManyAuthors(List<Author> authorList){
        try (Session s = sf.openSession()) {
            s.beginTransaction();

            for (int i = 0; i < authorList.size(); i++) {
                s.persist(authorList.get(i));

                if ((i + 1) % 10 == 0) {
                    s.flush();
                    s.clear();

                    System.out.println("flushed");
                }
            }

            s.getTransaction().commit();
            System.out.println("added many authors (" + authorList.size() + ")");

        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

}
