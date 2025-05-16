package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.OrderDetail;
import Models.Tour;

public class OrderDetailDAO extends DBContext {

    public void saveOrderDetail(OrderDetail orderDetail) {
        try {
            String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                    + "           ([Quantity]\n"
                    + "           ,[OrderID]\n"
                    + "           ,[TourID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, orderDetail.getQuantity());
            stm.setInt(2, orderDetail.getOrderID());
            stm.setInt(3, orderDetail.getTourID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Tour> getAllOrderDetailByOrderID(int id) {
        List<Tour> detail = new ArrayList<>();
        try {
            String sql = "SELECT * FROM OrderDetail OD JOIN Tour T \n"
                    + "ON OD.TourID = T.TourID\n"
                    + "WHERE OrderID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt(5));
                tour.setTourName(rs.getString(6));
                tour.setTourPrice(rs.getDouble(7));
                tour.setTourImage(rs.getString(9));
                tour.setQuantity(rs.getInt(2));
                detail.add(tour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detail;
    }
}
