package Controllers;

import DAL.CategoryDAO;
import DAL.LogDAO;
import Models.Account;
import Models.Category;
import Models.Log;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public class AddCategoryServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String categoryName = request.getParameter("categoryname").trim();
        CategoryDAO cdb = new CategoryDAO();
        List<Category> list = cdb.getAllCategories();
        boolean duplicate = false;
        for (Category c : list) {
            if (c.getCategoryName().equalsIgnoreCase(categoryName)) {
                request.setAttribute("error", "This category is exited");
                duplicate = true;
                break;
            }
        }
        if (!duplicate) {
            cdb.insertCategory(categoryName);
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            Log log = new Log();
            log.setAccountID(String.valueOf(acc.getAccountID()));
            log.setAction("Add new Category");
            log.setTime(String.valueOf(LocalDateTime.now()));
            LogDAO ldao = new LogDAO();
            ldao.createLog(log);
            response.sendRedirect("managecategory");
        }
        request.getRequestDispatcher("managecategory").forward(request, response);
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
