package hw3;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    static void main() {
        Animal animal = new Animal();
        animal.setAge(1);
        animal.setName("newAnimal");
        animal.setTail(true);

        SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session s = sf.openSession();
        s.beginTransaction();
        s.persist(animal);
        s.getTransaction().commit();
        s.close();

    }
}
