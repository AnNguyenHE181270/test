package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Models.Tour;

public class TourDAO extends DBContext {

    public List<Tour> getAllTours() {
        List<Tour> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Tour T JOIN Account A ON T.CreatedBy = A.AccountID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setTourImage(rs.getString("TourImage"));
                tour.setTourPrice(rs.getDouble("TourPrice"));
                tour.setTourDescription(rs.getString("TourDescription"));
                tour.setCategoryID(rs.getInt("CategoryID"));
                tour.setQuantity(rs.getInt("Quantity"));
                tour.setCreatedBy(rs.getString("Username"));
                list.add(tour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Tour> getToursByCategoryId(int categoryID) {
        List<Tour> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Tour T JOIN Account A ON T.CreatedBy = A.AccountID WHERE T.CategoryID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, categoryID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setTourImage(rs.getString("TourImage"));
                tour.setTourPrice(rs.getDouble("TourPrice"));
                tour.setTourDescription(rs.getString("TourDescription"));
                tour.setCategoryID(rs.getInt("CategoryID"));
                tour.setQuantity(rs.getInt("Quantity"));
                tour.setCreatedBy(rs.getString("Username"));
                list.add(tour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Tour> getToursWithPaging(int page, int pageSize) {
        List<Tour> list = new ArrayList<>();
        try {
            String sql = "SELECT *  FROM Tour WHERE Quantity > 0 ORDER BY TourID\n"
                    + "OFFSET (?-1)*? ROW FETCH NEXT ? ROWS ONLY";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, page);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setTourImage(rs.getString("TourImage"));
                tour.setTourPrice(rs.getDouble("TourPrice"));
                tour.setTourDescription(rs.getString("TourDescription"));
                tour.setCategoryID(rs.getInt("CategoryID"));
                tour.setQuantity(rs.getInt("Quantity"));
                list.add(tour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getTotalTours() {
        try {
            String sql = "SELECT COUNT(TourID) FROM Tour ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<Tour> search(String keyword) {
        List<Tour> list = new ArrayList<>();
        try {
            String sql = "SELECT *  FROM Tour WHERE TourName LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setTourImage(rs.getString("TourImage"));
                tour.setTourPrice(rs.getDouble("TourPrice"));
                tour.setTourDescription(rs.getString("TourDescription"));
                tour.setCategoryID(rs.getInt("CategoryID"));
                tour.setQuantity(rs.getInt("Quantity"));
                list.add(tour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Tour getTourById(int tourID) {
        try {
            String sql = "SELECT *  FROM Tour WHERE TourID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, tourID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setTourImage(rs.getString("TourImage"));
                tour.setTourPrice(rs.getDouble("TourPrice"));
                tour.setTourDescription(rs.getString("TourDescription"));
                tour.setCategoryID(rs.getInt("CategoryID"));
                tour.setQuantity(rs.getInt("Quantity"));
                return tour;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<Tour> getToursBySellerId(int sellerID) {
        List<Tour> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Tour T JOIN Account A ON T.CreatedBy = A.AccountID WHERE CreatedBy = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, sellerID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setTourImage(rs.getString("TourImage"));
                tour.setTourPrice(rs.getDouble("TourPrice"));
                tour.setTourDescription(rs.getString("TourDescription"));
                tour.setCategoryID(rs.getInt("CategoryID"));
                tour.setQuantity(rs.getInt("Quantity"));
                tour.setCreatedBy(rs.getString("Username"));
                list.add(tour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void insertTour(Tour tour) {
        try {
            String sql = "INSERT INTO [Tour]\n"
                    + "           ([TourName]\n"
                    + "           ,[TourImage]\n"
                    + "           ,[TourPrice]\n"
                    + "           ,[TourDescription]\n"
                    + "           ,[CategoryID]\n"
                    + "           ,[CreatedBy]\n"
                    + "           ,[Quantity])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, tour.getTourName());
            stm.setString(2, tour.getTourImage());
            stm.setDouble(3, tour.getTourPrice());
            stm.setString(4, tour.getTourDescription());
            stm.setInt(5, tour.getCategoryID());
            stm.setInt(6, Integer.parseInt(tour.getCreatedBy()));
            stm.setInt(7, tour.getQuantity());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTour(int tourID) {
        try {
            String sql = "DELETE FROM [Tour]\n"
                    + "WHERE TourID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, tourID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateTour(Tour tour) {
        try {
            String sql = "UPDATE [Tour]\n"
                    + "   SET [TourName] = ?\n"
                    + "      ,[TourImage] = ?\n"
                    + "      ,[TourPrice] = ?\n"
                    + "      ,[TourDescription] = ?\n"
                    + "      ,[CategoryID] = ?\n"
                    + "      ,[Quantity] = ?\n"
                    + " WHERE TourID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, tour.getTourName());
            stm.setString(2, tour.getTourImage());
            stm.setDouble(3, tour.getTourPrice());
            stm.setString(4, tour.getTourDescription());
            stm.setInt(5, tour.getCategoryID());
            stm.setInt(6, tour.getQuantity());
            stm.setInt(7, tour.getTourID());
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Tour> get4ToursLast() {
        List<Tour> list = new ArrayList<>();
        try {
            String sql = "SELECT TOP 4 * FROM Tour ORDER BY TourID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Tour tour = new Tour();
                tour.setTourID(rs.getInt("TourID"));
                tour.setTourName(rs.getString("TourName"));
                tour.setTourImage(rs.getString("TourImage"));
                tour.setTourPrice(rs.getDouble("TourPrice"));
                tour.setTourDescription(rs.getString("TourDescription"));
                tour.setCategoryID(rs.getInt("CategoryID"));
                tour.setQuantity(rs.getInt("Quantity"));
                list.add(tour);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Tour> getTourBestSeller() {
        List<Tour> list = new ArrayList<>();
        String sql = "SELECT TOP 5 T.TourID, SUM(OD.Quantity) AS Quantity FROM Tour T, OrderDetail OD\n"
                + "WHERE T.TourID = OD.TourID\n"
                + "GROUP BY T.TourID\n"
                + "ORDER BY Quantity DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Tour t = getTourById(rs.getInt(1));
                list.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TourDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
