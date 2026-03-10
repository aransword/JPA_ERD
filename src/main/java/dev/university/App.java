package dev.university;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hello-jpa");

        EntityManager em = emf.createEntityManager();

        System.out.println("DB 연결 성공");

        em.close();
        emf.close();
    }
}
