package jpa1;

import jpa1.entity.Flat;

import javax.persistence.*;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class AppFlat {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {

            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add flat");
                    System.out.println("2: edit flat");
                    System.out.println("3: search flat by params");
                    System.out.println("4: delete flat");
                    System.out.println("5: view all flats");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFlat(sc);
                            break;
                        case "2":
                            editFlat(sc);
                            break;
                        case "3":
                           // deleteClient(sc);
                            break;
                        case "4":
                            deleteFlat(sc);
                            break;
                        case "5":
                            viewAllFlats();
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addFlat(Scanner sc) {
        System.out.print("Enter name of district: ");
        String district = sc.nextLine();
        System.out.print("Enter adress: ");
        String adress = sc.nextLine();
        System.out.println("Enter area of flat");
        double area = Double.parseDouble(sc.nextLine());
        System.out.println("Enter the count of room");
        int countOfRoom = Integer.parseInt(sc.nextLine());
        System.out.println("Enter price of flat");
        int priceFlat = Integer.parseInt(sc.nextLine());

        em.getTransaction().begin();
        try {
            Flat flat = new Flat(district, adress, area, countOfRoom, priceFlat);
            em.persist(flat);
            em.getTransaction().commit();

            System.out.println(flat.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void deleteFlat(Scanner sc) {
        System.out.print("Enter Flat id: ");
        String sId = sc.nextLine();
        long id = Long.parseLong(sId);

        Flat flat = em.getReference(Flat.class, id);
        if (flat == null) {
            System.out.println("Flat not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(flat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void editFlat(Scanner sc) {
        System.out.print("Enter client name: ");
        String name = sc.nextLine();


        System.out.print("Enter new age: ");
        String sAge = sc.nextLine();
        int age = Integer.parseInt(sAge);

        SimpleClient c = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM SimpleClient x WHERE x.name = :name", SimpleClient.class);
            query.setParameter("name", name);

            c = (SimpleClient) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Client not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        ///........

        em.getTransaction().begin();
        try {
            c.setAge(age);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }



    private static void viewAllFlats() {
        Query query = em.createQuery(
                "SELECT f FROM Flat f", Flat.class);
        List<Flat> listAllFlat = (List<Flat>) query.getResultList();

        for (Flat flat : listAllFlat)
            System.out.println(flat);
    }

}
