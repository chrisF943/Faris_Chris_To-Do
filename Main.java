
import entity.Items;
import entity.TestItems;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        String choice;
        do{
            choice = getChoice();
            switch (choice){
                case "A":
                    System.out.println("Enter the title:");
                    String title = input.nextLine();

                    System.out.println("Enter the priority:");
                    int priority = Integer.parseInt(input.nextLine());

                    System.out.println("Enter the description:");
                    String description = input.nextLine();
                    Transaction transaction = null;
                    try (Session session = HibernateUtil.getSessionFactory().openSession()) {

                        // start a transaction
                        transaction = session.beginTransaction();
                        TestItems testItems = new TestItems();
                        testItems.setTitle(title);
                        testItems.setPriority(priority);
                        testItems.setDescription(description);
                        // save the TestItems objects
                        session.persist(testItems);
                        // commit transaction
                        transaction.commit();
                    } catch (Exception e) {
                        if (transaction != null) {
                            transaction.rollback();
                        }
                        e.printStackTrace();
                    }
                    break;
                case "D":
                    System.out.println("Enter the ID of the item you want to delete:");
                    int idToDelete = Integer.parseInt(input.nextLine());

                    Transaction deleteTransaction = null;
                    try (Session deleteSession = HibernateUtil.getSessionFactory().openSession()) {
                        deleteTransaction = deleteSession.beginTransaction();

                        // Retrieve the item based on the provided title
                        TestItems itemToDelete = deleteSession.createQuery("from TestItems where id = :id", TestItems.class)
                                .setParameter("id", idToDelete)
                                .uniqueResult();

                        if (itemToDelete != null) {
                            // Delete the item
                            deleteSession.delete(itemToDelete);
                            System.out.println("Item deleted successfully!");
                        } else {
                            System.out.println("Item with ID '" + idToDelete + "' not found.");
                        }

                        deleteTransaction.commit();
                    } catch (Exception e) {
                        if (deleteTransaction != null) {
                            deleteTransaction.rollback();
                        }
                        e.printStackTrace();
                    }
                    break;
                case "V":
                    Transaction viewTransaction = null;
                    try (Session viewSession = HibernateUtil.getSessionFactory().openSession()) {
                        viewTransaction = viewSession.beginTransaction();

                        // Retrieve all items from the database
                        List<TestItems> allItems = viewSession.createQuery("from TestItems", TestItems.class).list();

                        // Display the details of each item
                        System.out.println("All Items:");
                        for (TestItems item : allItems) {
                            System.out.println("ID: " + item.getId());
                            System.out.println("Title: " + item.getTitle());
                            System.out.println("Priority: " + item.getPriority());
                            System.out.println("Description: " + item.getDescription());
                            System.out.println("--------------------");
                        }

                        viewTransaction.commit();
                    } catch (Exception e) {
                        if (viewTransaction != null) {
                            viewTransaction.rollback();
                        }
                        e.printStackTrace();
                    }
                    break;
                case "Q":
                    System.out.println("Goodbye!");
                    break;
                default:
                    break;
            }
        }while(!choice.equals("Q"));
        input.close();

    }//end main

    private static String getChoice() {
        String menu = "---[A]dd an item to To-Do list.---\n"
                + "---[D]elete an item from To-Do list.---\n"
                + "---[V]iew the To-Do list.---\n"
                + "---[Q]uit.---\n";
        System.out.println(menu);
        return input.nextLine().toUpperCase();
    }//end getChoice

}//end class
