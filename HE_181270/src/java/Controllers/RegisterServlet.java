package Controllers;

import DAL.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.Account;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String rePassWord = request.getParameter("repassword");
        if (!passWord.equals(rePassWord)) {
            request.setAttribute("mess", "Pass not match!");
            request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
        } else {
            AccountDAO adb = new AccountDAO();
            Account a = adb.checkAccountExist(userName);
            if (a == null) {
                adb.insertAccount(userName, passWord);
                response.sendRedirect("login");
            } else {
                request.setAttribute("mess", "Account Exist!");
                request.getRequestDispatcher("Views/Login.jsp").forward(request, response);
            }
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
