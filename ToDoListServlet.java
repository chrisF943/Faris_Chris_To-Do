package org.example.webui;

import entity.TestItems;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name="org.example.webui.ToDoListServlet", value="/To-Do-List-Servlet")
public class ToDoListServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("item_name");
        String description = request.getParameter("item_description");
        Integer priority = null;

        String priorityParameter = request.getParameter("item_priority");
        if (priorityParameter != null && !priorityParameter.isEmpty()) {
            try {
                priority = Integer.valueOf(priorityParameter);
            } catch (NumberFormatException e) {
                // Handle parsing error if needed
                e.printStackTrace();
            }
        }

        // Insert data into the database
        insertItemIntoDatabase(name, description, priority);

        // Redirect the user to result.jsp
        response.sendRedirect("result.jsp");
    }

    private void insertItemIntoDatabase(String name, String description, Integer priority) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start transaction
            transaction = session.beginTransaction();

            // Create new TestItems object
            TestItems testItems = new TestItems();
            testItems.setTitle(name);
            testItems.setDescription(description);
            testItems.setPriority(priority != null ? priority : 0); // Set priority to 0 if null

            // Save the TestItems object
            session.persist(testItems);

            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Or log the exception for debugging
        }
    }
}
