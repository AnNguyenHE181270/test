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

public class HomePageServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final int PAGE_SIZE = 6;

        List<Category> listCategories = new CategoryDAO().getAllCategories();
        request.setAttribute("listCategories", listCategories);

        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null) {
            page = Integer.parseInt(pageStr);
        }

        TourDAO tourDAO = new TourDAO();
        List<Tour> listTours = tourDAO.getToursWithPaging(page, PAGE_SIZE);
        int totalTours = tourDAO.getTotalTours();
        int totalPage = totalTours / PAGE_SIZE;
        if (totalTours % PAGE_SIZE != 0) {
            totalPage += 1;
        }
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("Tours", listTours);

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
