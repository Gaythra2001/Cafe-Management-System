/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
/**
 *
 * @author HP
 */
public class DbOperations {
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
            } catch(Exception ex) {}
            JOptionPane.showMessageDialog( null,e,"Message",JOptionPane.ERROR_MESSAGE);
        }
        finally {
            try {
                if(st != null) st.close();
                if(con != null) con.close();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    
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
            e.printStackTrace();
           JOptionPane.showMessageDialog( null,e,"Message",JOptionPane.ERROR_MESSAGE);
           return null;
        }
    }
    
    
    
    
}
