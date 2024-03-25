package org.example.webui;

import entity.TestItems;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Enumeration;


@WebServlet(name="org.example.webui.DeleteServlet", value="/Delete-Servlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = null;

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            try {
                id = Integer.valueOf(parameterName);
            } catch (NumberFormatException e) {
                // Handle parsing error if needed
                e.printStackTrace();
            }
        }

        if (id == null) {
            // Handle missing ID error
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing ID parameter");
            return;
        }

        deleteFromDatabase(id);

        // Redirect the user to result.jsp
        response.sendRedirect("result.jsp");
    }

    private void deleteFromDatabase(Integer id) {
        Transaction deleteTransaction = null;
        try (Session deleteSession = HibernateUtil.getSessionFactory().openSession()) {
            deleteTransaction = deleteSession.beginTransaction();

            // Retrieve the item based on the provided id
            TestItems itemToDelete = deleteSession.get(TestItems.class, id);

            if (itemToDelete != null) {
                // Delete the item
                deleteSession.delete(itemToDelete);
            } else {
                // Handle item not found error
                // Can be logged or reported to the user as needed
            }

            deleteTransaction.commit();
        } catch (Exception e) {
            if (deleteTransaction != null) {
                deleteTransaction.rollback();
            }
            e.printStackTrace(); // Or log the exception for debugging
        }
    }
}