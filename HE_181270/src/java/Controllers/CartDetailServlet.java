package Controllers;

import DAL.TourDAO;
import Models.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Models.Tour;
import jakarta.servlet.http.Cookie;
import java.util.HashMap;

public class CartDetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        if (acc != null) {
            Cookie[] cookies = request.getCookies();
            HashMap<Tour, Integer> cart = new HashMap<>();
            TourDAO tdb = new TourDAO();

            for (Cookie c : cookies) {
                String cookieName = c.getName();
                String[] cookieParts = cookieName.split("-");
                if (cookieParts.length == 2 && cookieParts[0].equals(String.valueOf(acc.getAccountID()))) {
                    String tourID = cookieParts[1];
                    for (Tour t : tdb.getAllTours()) {
                        if (tourID.equals(String.valueOf(t.getTourID()))) {
                            cart.put(t, Integer.valueOf(c.getValue()));
                        }
                    }
                }
            }
            request.setAttribute("cart", cart);
            request.getRequestDispatcher("Views/Cart.jsp").forward(request, response);
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
