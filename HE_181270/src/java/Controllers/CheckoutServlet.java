package Controllers;

import DAL.LogDAO;
import DAL.OrderDAO;
import DAL.OrderDetailDAO;
import DAL.TourDAO;
import Models.Account;
import Models.Log;
import Models.Order;
import Models.OrderDetail;
import Models.Tour;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;

public class CheckoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] select = request.getParameterValues("select");
        String[] quantity = request.getParameterValues("quantity");
        if (select != null) {
            HashMap<Tour, Integer> cart = new HashMap<>();
            double totalMoney = 0;
            for (int i = 0; i < select.length; i++) {
                int tourID = Integer.parseInt(select[i]);
                int q = Integer.parseInt(quantity[i]);
                TourDAO tourDAO = new TourDAO();
                Tour tour = tourDAO.getTourById(tourID);
                cart.put(tour, q);
                totalMoney += tour.getTourPrice() * q;
            }
            request.setAttribute("cart", cart);
            request.setAttribute("totalMoney", totalMoney);
            request.getRequestDispatcher("Views/Checkout.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Plase choose an item to checkout");
            request.getRequestDispatcher("cartdetail").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Lưu Order
        String[] select = request.getParameterValues("select");
        String[] quantity = request.getParameterValues("quantity");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        Cookie arr[] = request.getCookies();
        HashMap<Tour, Integer> cart = new HashMap<>();
        double totalMoney = 0;
        for (int i = 0; i < select.length; i++) {
            int tourID = Integer.parseInt(select[i]);
            int q = Integer.parseInt(quantity[i]);
            TourDAO tourDAO = new TourDAO();
            Tour tour = tourDAO.getTourById(tourID);
            cart.put(tour, q);
            totalMoney += tour.getTourPrice() * q;
            //Xóa Cookie
            for (Cookie c : arr) {
                if (c.getName().equals(select[i])) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        
        Order order = new Order("", totalMoney, String.valueOf(java.time.LocalDate.now()), acc.getAccountID(), "Processing");
        OrderDAO orderDAO = new OrderDAO();
        int orderId = orderDAO.createOrder(order);
        //Lưu OrderDetail (toán tử lambda)
        cart.forEach((tour, q) -> {
            OrderDetail detail = new OrderDetail("", q, orderId, tour.getTourID());
            OrderDetailDAO detailDAO = new OrderDetailDAO();
            detailDAO.saveOrderDetail(detail);
        });

        Log log = new Log();
        log.setAccountID(String.valueOf(acc.getAccountID()));
        log.setAction("Create new Order");
        log.setTime(String.valueOf(LocalDateTime.now()));
        LogDAO ldao = new LogDAO();
        ldao.createLog(log);
        request.getRequestDispatcher("Views/Thanks.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
