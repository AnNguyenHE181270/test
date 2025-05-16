package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Order;

public class OrderDAO extends DBContext {

    public int createOrder(Order order) {
        try {
            String sql = "INSERT INTO [dbo].[Order]\n"
                    + "           ([TotalPrice]\n"
                    + "           ,[CreateDate]\n"
                    + "           ,[AccountID]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stm.setDouble(1, order.getTotalPrice());
            stm.setString(2, order.getCreateDate());
            stm.setInt(3, order.getAccountID());
            stm.setString(4, order.getStatus());
            stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Order> getAllOrder() {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Order]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(String.valueOf(rs.getInt("OrderID")));
                order.setTotalPrice(rs.getDouble("TotalPrice"));
                order.setCreateDate(rs.getString("CreateDate"));
                order.setAccountID(rs.getInt("AccountID"));
                order.setStatus(rs.getString("Status"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public List<Order> getOrderByAccount(int account) {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Order] WHERE AccountID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, account);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOrderID(String.valueOf(rs.getInt("OrderID")));
                order.setTotalPrice(rs.getDouble("TotalPrice"));
                order.setCreateDate(rs.getString("CreateDate"));
                order.setAccountID(rs.getInt("AccountID"));
                order.setStatus(rs.getString("Status"));
                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public Order getOrderByID(int id) {
        try {
            String sql = "SELECT * FROM [Order] WHERE OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Order order = new Order();
                order.setOrderID(String.valueOf(rs.getInt("OrderID")));
                order.setTotalPrice(rs.getDouble("TotalPrice"));
                order.setCreateDate(rs.getString("CreateDate"));
                order.setAccountID(rs.getInt("AccountID"));
                order.setStatus(rs.getString("Status"));
                return order;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateOrder(int id, String status) {
        try {
            String sql = "UPDATE [dbo].[Order]\n"
                    + "   SET [Status]= ?\n"
                    + " WHERE OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Integer> getTop5UserBuyMost() {
        List<Integer> list = new ArrayList<>();
        int accountID = 0;
        String sql = "SELECT TOP 5 COUNT(AccountID), O.AccountID FROM [Order] O\n"
                + "GROUP BY  O.AccountID\n"
                + "ORDER BY COUNT(AccountID) DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                accountID = rs.getInt(2);
                list.add(accountID);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
