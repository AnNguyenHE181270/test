package Controllers;

import DAL.CategoryDAO;
import DAL.TourDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import Models.Account;
import Models.Category;
import Models.Tour;

public class ManageTourServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("acc");
        TourDAO sdb = new TourDAO();
        List<Tour> tours = new ArrayList<>();
        if (a.isIsAdmin()) {
            tours = sdb.getAllTours();
        } else if (a.isIsSeller()) {
            tours = sdb.getToursBySellerId(a.getAccountID());
        }
        List<Category> listCategories = new CategoryDAO().getAllCategories();
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("tours", tours);
        request.getRequestDispatcher("Views/ManageTour.jsp").forward(request, response);
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
