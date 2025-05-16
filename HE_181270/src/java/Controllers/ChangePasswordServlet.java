package Controllers;

import DAL.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.Account;

public class ChangePasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/Views/ChangePassword.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String oldPass = request.getParameter("oldpass");
        String newPass = request.getParameter("newpass");
        String repass = request.getParameter("repass");
        AccountDAO adb = new AccountDAO();
        Account account = adb.checkAccountExistByUserPass(username, oldPass);
        if (account == null) {
            request.setAttribute("mess", "Account does not exist or wrong password !");
            request.getRequestDispatcher("Views/ChangePassword.jsp").forward(request, response);
            return;
        }
        if (!newPass.equals(repass)) {
            request.setAttribute("mess", "Password does not match!");
            request.getRequestDispatcher("Views/ChangePassword.jsp").forward(request, response);
            return;
        }
        if (newPass.equals(repass)) {
            adb.updatePassword(newPass, username);
            request.setAttribute("mess", "Change password successfully!");
            request.getRequestDispatcher("Views/ChangePassword.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
