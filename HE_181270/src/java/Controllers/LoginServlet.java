package Controllers;

import DAL.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Models.Account;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        AccountDAO adb = new AccountDAO();
        Account account = adb.login(userName, passWord);
        if (account == null) {
            request.setAttribute("mess", "Wrong user or pass");
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
        } else if (account.isIsActive() == false) {
            request.setAttribute("mess", "Account has been banned");
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("acc", account);
            response.sendRedirect("home");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
