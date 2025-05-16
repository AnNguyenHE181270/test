package Controllers;

import DAL.AccountDAO;
import DAL.OrderDAO;
import DAL.TourDAO;
import Models.Account;
import Models.Order;
import Models.Tour;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class DashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        double earning = 0;
        int success = 0;
        int pending = 0;
        List<Order> orders = orderDAO.getAllOrder();
        for (Order order : orders) {
            if (order.getStatus().equals("Completed")) {
                success++;
                earning += order.getTotalPrice();
            }
            if (order.getStatus().equals("Processing")) {
                pending++;
            }
        }
        List<Integer> list = orderDAO.getTop5UserBuyMost();
        List<Account> listUser = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            AccountDAO accountDAO = new AccountDAO();
            Account u = accountDAO.getAccountById(list.get(i));
            listUser.add(u);
        }
        TourDAO tourDAO = new TourDAO();
        List<Tour> listTour = tourDAO.getTourBestSeller();
        request.setAttribute("earning", earning);
        request.setAttribute("success", success);
        request.setAttribute("pending", pending);
        request.setAttribute("listUser", listUser);
        request.setAttribute("listTour", listTour);
        request.getRequestDispatcher("Views/Dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
