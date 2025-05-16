package Controllers;

import DAL.LogDAO;
import DAL.OrderDAO;
import DAL.OrderDetailDAO;
import DAL.TourDAO;
import Models.Account;
import Models.Log;
import Models.Order;
import Models.Tour;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProcessOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        OrderDetailDAO orderdetailDAO = new OrderDetailDAO();
        List<Tour> tours = orderdetailDAO.getAllOrderDetailByOrderID(id);
        OrderDAO orderDAO = new OrderDAO();
        Order order = orderDAO.getOrderByID(id);
        request.setAttribute("order", order);
        request.setAttribute("tours", tours);
        request.getRequestDispatcher("Views/ProcessOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String[] tourID = request.getParameterValues("tourID");
        String[] quantity = request.getParameterValues("quantity");
        String btnCheck = request.getParameter("check");
        String btnAccept = request.getParameter("accept");
        String btnDenied = request.getParameter("denied");
        TourDAO tourDAO = new TourDAO();
        OrderDAO orderDAO = new OrderDAO();
        Log log = new Log();
        LogDAO ldao = new LogDAO();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (btnCheck != null) {
            List<String> message = new ArrayList<>();
            for (int i = 0; i < tourID.length; i++) {
                int tid = Integer.parseInt(tourID[i]);
                int q = Integer.parseInt(quantity[i]);
                Tour tour = tourDAO.getTourById(tid);
                if (tour.getQuantity() < q) {
                    message.add("Out of Stock");
                } else {
                    message.add("Available");
                }
                request.setAttribute("message", message);
                request.setAttribute("tid", tid);
            }
            doGet(request, response);
        }
        if (btnAccept != null) {
            orderDAO.updateOrder(id, "Completed");
            for (int i = 0; i < tourID.length; i++) {
                int tid = Integer.parseInt(tourID[i]);
                Tour tour = tourDAO.getTourById(tid);
                tour.setQuantity(tour.getQuantity() - Integer.parseInt(quantity[i]));
                tourDAO.updateTour(tour);

                log.setAccountID(String.valueOf(acc.getAccountID()));
                log.setAction("Accept Order with id=" + id);
                log.setTime(String.valueOf(LocalDateTime.now()));
                ldao.createLog(log);
            }
        }
        if (btnDenied != null) {
            orderDAO.updateOrder(id, "Canceled");

            log.setAccountID(String.valueOf(acc.getAccountID()));
            log.setAction("Deny Order with id=" + id);
            log.setTime(String.valueOf(LocalDateTime.now()));
            ldao.createLog(log);
        }
        response.sendRedirect("manageorder");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
