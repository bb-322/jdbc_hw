package hw6.task35;

import hw6.task35.entity.Book;

import jakarta.persistence.criteria.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class BookHelper {

    private SessionFactory sf;

    public BookHelper() {
        sf = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList() {
        try (Session session = sf.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Book> cq = cb.createQuery(Book.class);
            Root<Book> root = cq.from(Book.class);

            cq.select(root);

            return session.createQuery(cq).getResultList();
        }
    }

    public Book getBookById(long id) {
        try (Session s = sf.openSession()) {
            return s.find(Book.class, id);
        }
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

    public void deleteBook(long id) {
        try (Session s = sf.openSession()) {
            s.beginTransaction();

            Book b = s.find(Book.class, id);
            if (b != null) {
                s.remove(b);
            }

            s.getTransaction().commit();
        }
    }

    public void deleteBookByAuthor(long authorId) {
        try (Session s = sf.openSession()) {
            s.beginTransaction();

            s.createMutationQuery(
                            "delete from Book b where b.author.id = :authorId"
                    )
                    .setParameter("authorId", authorId)
                    .executeUpdate();

            s.getTransaction().commit();
        }
    }

    public List<Book> searchBooks(String searchText) {
        try (Session s = sf.openSession()) {

            CriteriaBuilder cb = s.getCriteriaBuilder();
            CriteriaQuery<Book> cq = cb.createQuery(Book.class);
            Root<Book> root = cq.from(Book.class);

            cq.select(root).where(cb.like(root.get("name"),  "%" + searchText + "%"));
            return s.createQuery(cq).getResultList();
        }
    }

}
