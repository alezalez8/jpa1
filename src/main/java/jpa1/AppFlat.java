package jpa1;

import jpa1.entity.Flat;

import javax.persistence.*;
import java.util.*;

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
                    System.out.println("1: add flat");  // OK
                    System.out.println("2: edit flat"); // OK
                    System.out.println("3: search flat by params"); // OK
                    System.out.println("4: delete flat");  // OK
                    System.out.println("5: view all flats"); // OK
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
                            searchByParams(sc);
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
        System.out.print("Enter flat id: ");
        String id = sc.nextLine();
        long currentId = Long.parseLong(id);

        System.out.print("Enter new name of district: ");
        String district = sc.nextLine();
        System.out.print("Enter new adress: ");
        String adress = sc.nextLine();
        System.out.println("Enter new area of flat");
        double area = Double.parseDouble(sc.nextLine());
        System.out.println("Enter the new count of room");
        int countOfRoom = Integer.parseInt(sc.nextLine());
        System.out.println("Enter new price of flat");
        int priceFlat = Integer.parseInt(sc.nextLine());

        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT f FROM Flat f WHERE f.id = :id", Flat.class);
            query.setParameter("id", currentId);


            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setDistrictOfCity(district);
            flat.setAdress(adress);
            flat.setAreaOfFlat(area);
            flat.setCountOfRooms(countOfRoom);
            flat.setPrice(priceFlat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void searchByParams(Scanner sc) {
        String sql = myQuerry(sc);
       // System.out.println("My sql is: " + sql);
        String sql1 = "SELECT f FROM Flat f " + sql;
        System.out.println("===================================");
        try {
            Query query = em.createQuery(sql1);
            List<Flat> listAllFlat = (List<Flat>) query.getResultList();

            for (Flat flat : listAllFlat)
                System.out.println(flat);
            System.out.println("====================================");

        } catch (Exception e) {
            System.err.println("Not correct query");
        }
    }

    private static void viewAllFlats() {
        Query query = em.createQuery(
                "SELECT f FROM Flat f", Flat.class);
        List<Flat> listAllFlat = (List<Flat>) query.getResultList();

        for (Flat flat : listAllFlat)
            System.out.println(flat);
    }

    private static String myQuerry(Scanner scanner) {
        StringBuilder myQurry = new StringBuilder("WHERE ");

        for (; ; ) {
            System.out.println("Choose option for search and its value (use space between option and value)");
            System.out.println("1: search by district of city");
            System.out.println("2: search by adress");
            System.out.println("3: search by area of flat");
            System.out.println("4: search by the count of rooms");
            System.out.println("5: search by price");
            System.out.print("-> ");
            String stroka = scanner.nextLine();
            String[] search = stroka.split("[ .]");
            StringBuilder value = new StringBuilder(stroka);
            value.delete(0, 2);

            switch (search[0]) {
                case "1":
                    myQurry.append("f.").append("districtOfCity=")
                            .append("\'").append(value).append("\'").append(" AND ");
                    break;
                case "2":
                    myQurry.append("f.").append("adress=")
                            .append("\'").append(value).append("\'").append(" AND ");
                    break;
                case "3":
                    myQurry.append("f.").append("areaOFlat=").append(search[1]).append(" AND ");
                    break;
                case "4":
                    myQurry.append("f.").append("countOfRooms=").append(search[1]).append(" AND ");
                    break;
                case "5":
                    myQurry.append("f.").append("price=").append(search[1]).append(" AND ");
                    break;
                case "":
                    return myQurry.delete(myQurry.length() - 5, myQurry.length() - 1).toString();

            }
        }

    }

}
