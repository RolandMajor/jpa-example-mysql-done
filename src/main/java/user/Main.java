package user;

import com.github.javafaker.Faker;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import user.model.User;


public class Main {

    private static Faker faker = new Faker();

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("legoset-mysql");
        EntityManager em = emf.createEntityManager();

        User user = User.builder().username("linus").password("123456").build();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        System.out.println(user);

        em.getTransaction().begin();
        user.setPassword("secret");
        em.getTransaction().commit();

        System.out.println(em.find(User.class, "linus"));
        System.out.println(user == em.find(User.class, "linus"));

        em.clear();

        System.out.println(em.find(User.class, "linus"));
        System.out.println(user == em.find(User.class, "linus"));

        em.getTransaction().begin();
        //em.merge(user);
        //user.setPassword("ApW6,#g>1");
        User managedUser = em.merge(user);
        managedUser.setPassword("ApW6,#g>1");
        em.getTransaction().commit();
        System.out.println(em.find(User.class, "linus"));

        em.close();
        emf.close();
    }

}
