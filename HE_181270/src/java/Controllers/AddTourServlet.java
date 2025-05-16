package Controllers;

import DAL.LogDAO;
import DAL.TourDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Models.Account;
import Models.Tour;
import Models.Log;
import java.time.LocalDateTime;

public class AddTourServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("Views/ManageTour.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tourName = request.getParameter("tourName");
        String tourImage = "Images/" + request.getParameter("tourImage");
        double tourPrice = Double.parseDouble(request.getParameter("tourPrice"));
        int tourQuantity = Integer.parseInt(request.getParameter("tourQuantity"));
        String description = request.getParameter("tourDescription");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        //Create Tour
        Tour tour = new Tour();
        tour.setTourName(tourName);
        tour.setTourImage(tourImage);
        tour.setTourPrice(tourPrice);
        tour.setQuantity(tourQuantity);
        tour.setCategoryID(categoryID);
        tour.setTourDescription(description);
        tour.setCreatedBy(String.valueOf(acc.getAccountID()));
        TourDAO pdb = new TourDAO();
        pdb.insertTour(tour);

        //Add Log
        Log log = new Log();
        log.setAccountID(String.valueOf(acc.getAccountID()));
        log.setAction("Add new Tour");
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
