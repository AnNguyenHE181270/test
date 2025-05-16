package Controllers;

import DAL.CategoryDAO;
import DAL.LogDAO;
import Models.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.Category;
import Models.Log;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class EditCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cid = request.getParameter("cid");
        Category category = new CategoryDAO().getCategoryById(Integer.parseInt(cid));
        request.setAttribute("category", category);
        request.getRequestDispatcher("/Views/EditCategory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category category = new Category();
        String categoryID = request.getParameter("categoryID");
        category.setCategoryID(Integer.parseInt(categoryID));
        category.setCategoryName(request.getParameter("categoryName"));
        CategoryDAO cdb = new CategoryDAO();
        cdb.updateCategory(category);

//        Create Log
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        Log log = new Log();
        log.setAccountID(String.valueOf(acc.getAccountID()));
        log.setAction("With Category with id=" + categoryID);
        log.setTime(String.valueOf(LocalDateTime.now()));
        LogDAO ldao = new LogDAO();
        ldao.createLog(log);
        response.sendRedirect("managecategory");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
