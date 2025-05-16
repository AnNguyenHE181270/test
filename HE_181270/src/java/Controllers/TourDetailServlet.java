package Controllers;

import DAL.TourDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Models.Tour;

public class TourDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int tourID = Integer.parseInt(request.getParameter("tourID"));
        request.getSession().setAttribute("urlHistory", "detail?tourID=" + tourID);
        TourDAO pdb = new TourDAO();
        Tour tour = pdb.getTourById(tourID);
        List<Tour> listLast = pdb.get4ToursLast();
        request.setAttribute("tour", tour);
        request.setAttribute("listLast", listLast);
        request.getRequestDispatcher("Views/TourDetail.jsp").forward(request, response);
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
