package Controllers;

import DAL.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.Account;

public class EditAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accountID = request.getParameter("accountID");
        Account account = new AccountDAO().getAccountById(Integer.parseInt(accountID));
        request.setAttribute("account", account);
        request.getRequestDispatcher("Views/EditAccount.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = new Account();
        String x = request.getParameter("isSeller");
        System.out.println(x);
        account.setAccountID(Integer.parseInt(request.getParameter("accountID")));
        account.setUsername(request.getParameter("username"));
        account.setPassword(request.getParameter("password"));
        account.setIsSeller(Boolean.parseBoolean(request.getParameter("isSeller")));
        account.setIsAdmin(false);
        account.setIsActive(Boolean.parseBoolean(request.getParameter("isActive")));

        AccountDAO adb = new AccountDAO();
        adb.updateAccount(account);
        response.sendRedirect("manageaccount");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
