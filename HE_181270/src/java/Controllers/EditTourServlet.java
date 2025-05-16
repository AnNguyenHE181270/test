package Controllers;

import DAL.CategoryDAO;
import DAL.LogDAO;
import DAL.TourDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import Models.Account;
import Models.Category;
import Models.Log;
import Models.Tour;
import java.time.LocalDateTime;

public class EditTourServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tourID = request.getParameter("tourID");
        Tour tour = new TourDAO().getTourById(Integer.parseInt(tourID));
        request.setAttribute("tour", tour);
        List<Category> listCategories = new CategoryDAO().getAllCategories();
        request.setAttribute("listCategories", listCategories);
        request.getRequestDispatcher("/Views/EditTour.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int tourID = Integer.parseInt(request.getParameter("tourID"));
        String tourName = request.getParameter("tourName");
        String tourImage = "Images/" + request.getParameter("tourImage");
        double tourPrice = Double.parseDouble(request.getParameter("tourPrice"));
        int tourQuantity = Integer.parseInt(request.getParameter("tourQuantity"));
        String tourDescription = request.getParameter("tourDescription");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));

//        Edit Tour
        Tour tour = new TourDAO().getTourById(tourID);
        tour.setTourName(tourName);
        tour.setTourImage(tourImage);
        tour.setTourPrice(tourPrice);
        tour.setQuantity(tourQuantity);
        tour.setTourDescription(tourDescription);
        tour.setCategoryID(categoryID);
        TourDAO pdb = new TourDAO();
        pdb.updateTour(tour);

//        Create Log
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        Log log = new Log();
        log.setAccountID(String.valueOf(acc.getAccountID()));
        log.setAction("Edit tour with id=" + tourID);
        log.setTime(String.valueOf(LocalDateTime.now()));
        LogDAO ldao = new LogDAO();
        ldao.createLog(log);
        response.sendRedirect("managetour");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
