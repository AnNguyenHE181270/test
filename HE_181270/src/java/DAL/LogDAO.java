package DAL;

import java.util.ArrayList;
import java.util.List;
import Models.Log;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.LocalDateTime;

public class LogDAO extends DBContext {

    public List<Log> getAllLogs() {
        List<Log> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Log L JOIN Account A ON L.AccountID = A.AccountID";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Log log = new Log();
                log.setLogID(String.valueOf(rs.getInt(1)));
                log.setAccountID(String.valueOf(rs.getString(6)));
                log.setAction(String.valueOf(rs.getString(3)));
                log.setTime(String.valueOf(rs.getObject(4)));
                list.add(log);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Log> getLogsByAccountId(int accountID) {
        List<Log> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Log WHERE AccountID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, accountID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Log log = new Log();
                log.setLogID(String.valueOf(rs.getInt(1)));
                log.setAccountID(String.valueOf(rs.getString(6)));
                log.setAction(String.valueOf(rs.getString(3)));
                log.setTime(String.valueOf(rs.getObject(4)));
                list.add(log);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public void createLog(Log log) {
        try {
            String sql = "INSERT INTO Log([AccountID], [Action], [Time]) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(log.getAccountID()));
            stm.setString(2, log.getAction());
            stm.setObject(3, LocalDateTime.parse(log.getTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteLog(int logID) {
        try {
            String sql = "DELETE FROM Log WHERE LogID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, logID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LogDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
