package Controllers;

import DAL.CategoryDAO;
import DAL.TourDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Models.Category;
import Models.Tour;

public class CategoryDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        List<Tour> Tours = new TourDAO().getToursByCategoryId(categoryID);
        List<Category> listCategories = new CategoryDAO().getAllCategories();
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("Tours", Tours);
        request.setAttribute("tag", categoryID);
        request.getRequestDispatcher("Views/HomePage.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
