package hw4.task7;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.*;

import java.util.List;

public class AnimalHelper {

    private EntityManagerFactory emf;

    public AnimalHelper() {
        emf = JPAUtil.getEntityManager().getEntityManagerFactory();
    }

    public List<Animal> getAnimalList() {

        EntityManager em = emf.createEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);

        Root<Animal> root = cq.from(Animal.class);
        cq.select(root);

        List<Animal> result = em.createQuery(cq).getResultList();

        em.close();

        return result;
    }

    public Animal getAnimalById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Animal.class, id);
    }

    public void addAnimal(Animal animal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(animal);

        em.getTransaction().commit();

        em.close();
    }

    public void updateAnimal(Animal animal, int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        Animal a = em.find(Animal.class, id);
        a.setName(animal.getName());
        a.setAge(animal.getAge());
        a.setTail(animal.isTail());

        em.getTransaction().commit();
        em.close();
    }

    public void deleteAnimal(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Animal a = em.find(Animal.class, id);
        em.remove(a);

        em.getTransaction().commit();
        em.close();
    }

}
