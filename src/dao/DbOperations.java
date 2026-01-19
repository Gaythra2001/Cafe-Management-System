package dao;

import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.util.logging.Logger;
/**
 *
 * @author HP
 */
public class DbOperations {
    private static final Logger LOGGER = Logger.getLogger(DbOperations.class.getName());
    
    @SuppressWarnings("deprecation")
    public static void setDataOrDelete(String Query,String msg){
        Connection con = null;
        Statement st = null;
        try{
          con = ConnectionProvider.getCon();
          if(con == null) {
              JOptionPane.showMessageDialog(null, "Database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
              return;
          }
          con.setAutoCommit(true); // Enable autocommit for immediate persistence
          st = con.createStatement();
          st.executeUpdate(Query);
          con.commit(); // Explicitly commit the transaction
          if(!msg.equals(""))
              JOptionPane.showMessageDialog(null, msg);
          
        }
        catch(Exception e){
            try {
                if(con != null) con.rollback(); // Rollback on error
            } catch(Exception ex) {
                LOGGER.warning("Rollback failed: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog( null,e,"Message",JOptionPane.ERROR_MESSAGE);
            LOGGER.severe("Database operation error: " + e.getMessage());
        }
        finally {
            try {
                if(st != null) st.close();
                if(con != null) con.close();
            } catch(Exception e) {
                LOGGER.warning("Error closing resources: " + e.getMessage());
            }
        }
    }
    
    @SuppressWarnings("deprecation")
    public static ResultSet getData(String query){
        
        try{
            Connection con = ConnectionProvider.getCon();
            if(con == null) {
                JOptionPane.showMessageDialog(null, "Database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            con.setAutoCommit(true);
            Statement st = con.createStatement();
            st.setQueryTimeout(30); // Set query timeout to 30 seconds
            ResultSet rs = st.executeQuery(query);
            return rs;
        }
        
        catch(Exception e){
           JOptionPane.showMessageDialog( null,e,"Message",JOptionPane.ERROR_MESSAGE);
           LOGGER.severe("Database query error: " + e.getMessage());
           return null;
        }
    }
}
