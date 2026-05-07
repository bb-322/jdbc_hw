package hw4.ex_003_hibernate_get_and_insert;

import hw4.ex_003_hibernate_get_and_insert.entity.Book;
import hw4.ex_003_hibernate_get_and_insert.entity.Author;

import jakarta.persistence.criteria.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.criteria.*;

import java.util.List;

public class BookHelper {

    private SessionFactory sf;

    public BookHelper() {
        sf = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList(){
        Session session = sf.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> root = cq.from(Book.class);
        cq.select(root);
        List<Book> bookList = session.createQuery(cq).getResultList();

        session.close();

        return bookList;
    }

    public Book getBookById(long id) {
        Session s = sf.openSession();
        return s.find(Book.class, id);
    }

    public void addBook(Book book){
        try (Session s = sf.openSession()) {
            s.beginTransaction();

            s.persist(book);

            s.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
