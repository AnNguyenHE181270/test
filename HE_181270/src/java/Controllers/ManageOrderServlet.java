package Controllers;

import DAL.AccountDAO;
import DAL.OrderDAO;
import Models.Account;
import Models.Order;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orderList = orderDAO.getAllOrder();
        AccountDAO accountDAO = new AccountDAO();
        List<Account> accountList = accountDAO.getAllAccount();
        request.setAttribute("accountList", accountList);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("Views/ManageOrder.jsp").forward(request, response);
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
