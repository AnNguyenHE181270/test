package Controllers;

import Models.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class EditCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");

        String id = request.getParameter("id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        Cookie[] cookies = request.getCookies();
        // Tìm và xóa cookie cũ
        for (Cookie c : cookies) {
            String cookieName = c.getName();
            String[] cookieParts = cookieName.split("-");
            if (cookieParts.length == 2 && cookieParts[0].equals(String.valueOf(acc.getAccountID()))) {
                String tourID = cookieParts[1];
                if (tourID.equals(id)) {
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        // Tạo cookie mới với số lượng đã được chỉnh sửa
        String cookieName = acc.getAccountID() + "-" + id;
        Cookie newCookie = new Cookie(cookieName, String.valueOf(quantity));
        newCookie.setMaxAge(60 * 60 * 24 * 365);
        response.addCookie(newCookie);

        // Chuyển hướng đến trang "cartdetail"
        response.sendRedirect("cartdetail");
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
