package Controllers;

import Models.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;

public class AddToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        if (acc != null) {
            String tourID = request.getParameter("tourID");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Cookie[] cookies = request.getCookies();
            int currentQuantity = 0;
            String cookieName = acc.getAccountID() + "-" + tourID; // Sử dụng ID của tài khoản để phân biệt cookie

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(cookieName)) {
                    currentQuantity = Integer.parseInt(cookie.getValue());
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }

            currentQuantity += quantity;
            Cookie newCookie = new Cookie(cookieName, String.valueOf(currentQuantity));
            response.addCookie(newCookie);
            newCookie.setMaxAge(60 * 60 * 24 * 365);
            response.sendRedirect("cartdetail");
        } else {
            response.sendRedirect("login");
        }
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
