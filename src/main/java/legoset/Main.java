package legoset;

import java.time.Year;
import java.util.List;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import legoset.model.LegoSet;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Main {

    private static Logger logger = LogManager.getLogger();

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("legoset-mysql");

    private static void createLegoSets() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new LegoSet("60073", "Service Truck", Year.of(2015), 233));
            em.persist(new LegoSet("75211", "Imperial TIE Fighter", Year.of(2018), 519));
            em.persist(new LegoSet("21034", "London", Year.of(2017), 468));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static List<LegoSet> getLegoSets() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT l FROM LegoSet l ORDER BY l.number", LegoSet.class).getResultList();
        } finally {
            em.close();
        }
    }

    private static Long getTotalPieces() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT SUM(pieces) FROM LegoSet l", Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    private static void deleteLegoSets() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            long count = em.createQuery("DELETE FROM LegoSet").executeUpdate();
            logger.info("Deleted {} LEGO set(s)", count);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void main(String[] args) {
        createLegoSets();
        getLegoSets().forEach(logger::info);
        logger.info("Total pieces: {}", getTotalPieces());
        deleteLegoSets();
        emf.close();
    }

}
