package Controllers;

import DAL.OrderDAO;
import Models.Account;
import Models.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

public class TrackOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getOrderByAccount(acc.getAccountID());
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("Views/TrackOrder.jsp").forward(request, response);;
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
