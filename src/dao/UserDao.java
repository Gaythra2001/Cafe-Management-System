/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import javax.swing.JOptionPane;
import model.User;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class UserDao {
     @SuppressWarnings("deprecation")
     public static void save(User user){
        String Query = "insert into user(name,email,mobileNumber,address,password,securityQuestion,answer,status) values(?,?,?,?,?,?,?,'false')";
        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement(Query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getMobileNumber());
            ps.setString(4, user.getAddress());
            ps.setString(5, user.getPassword());
            ps.setString(6, user.getSecurityQuestion());
            ps.setString(7, user.getAnswer());
            ps.executeUpdate();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null,"Account Created Successfully!\nUsername: " + user.getName() + "\nWait for Admin Approval!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error creating account: " + e.getMessage());
        }
    }
     
     @SuppressWarnings("deprecation")
     public static User login(String email,String password){
         User user = null;
         try{
            String query = "select * from user where email=? and password=?";
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user = new User();
                user.setStatus(rs.getString("status"));
                user.setEmail(rs.getString("email"));
            }
            rs.close();
            ps.close();
            con.close();
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,"Login error: " + e.getMessage());
         }
         return user;
     }
     
     @SuppressWarnings("deprecation")
     public static User loginWithUsername(String username,String password){
         User user = null;
         try{
            String query = "select * from user where name=? and password=?";
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user = new User();
                user.setStatus(rs.getString("status"));
                user.setEmail(rs.getString("email"));
                user.setName(rs.getString("name"));
            }
            rs.close();
            ps.close();
            con.close();
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,"Login error: " + e.getMessage());
         }
         return user;
     }
     
     @SuppressWarnings("deprecation")
     public static User getSecurityQuestion(String email){
         User user = null;
         try{
             String query = "select * from user where email = ?";
             Connection con = ConnectionProvider.getCon();
             PreparedStatement ps = con.prepareStatement(query);
             ps.setString(1, email);
             ResultSet rs = ps.executeQuery();
             while(rs.next()){
                 user = new User();
                 user.setSecurityQuestion(rs.getString("securityQuestion"));
                 user.setAnswer(rs.getString("answer"));
             }
             rs.close();
             ps.close();
             con.close();
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());
         }
         return user;
     }
     
     @SuppressWarnings("deprecation")
     public static void update(String email,String newPassword){
         String query = "update user set password = ? where email=?";
         try {
             Connection con = ConnectionProvider.getCon();
             PreparedStatement ps = con.prepareStatement(query);
             ps.setString(1, newPassword);
             ps.setString(2, email);
             ps.executeUpdate();
             ps.close();
             con.close();
             JOptionPane.showMessageDialog(null,"Password Changed Successfully");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
         }
     }
     
     @SuppressWarnings("deprecation")
     public static void changePassword(String email,String oldPassword,String newPassword){
         try{
             String query = "Select * from user where email=? and password=?";
             Connection con = ConnectionProvider.getCon();
             PreparedStatement ps = con.prepareStatement(query);
             ps.setString(1, email);
             ps.setString(2, oldPassword);
             ResultSet rs = ps.executeQuery();
             if(rs.next()){
                 update(email,newPassword);
             }   
             else{
                 JOptionPane.showMessageDialog(null,"Old Password is Wrong");
             }
             rs.close();
             ps.close();
             con.close();
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());
         }
     }
     
     @SuppressWarnings("deprecation")
     public static void changeSecurityQuestion(String email,String password,String securityQuestion,String answer){
         try{
            String query = "select * from user where email=? and password=?";
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                update(email,securityQuestion,answer);
            }
            else{
                JOptionPane.showMessageDialog(null,"Password is Wrong");
            }
            rs.close();
            ps.close();
            con.close();
         }
         catch(Exception e){
             JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());
         }
     }
     
     @SuppressWarnings("deprecation")
     public static void update(String email,String securityQuestion,String answer){
         String query = "update user set securityQuestion=?,answer=? where email=?";
         try {
             Connection con = ConnectionProvider.getCon();
             PreparedStatement ps = con.prepareStatement(query);
             ps.setString(1, securityQuestion);
             ps.setString(2, answer);
             ps.setString(3, email);
             ps.executeUpdate();
             ps.close();
             con.close();
             JOptionPane.showMessageDialog(null,"Security Question Changed Successfully");
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
         }
     }
     
     @SuppressWarnings("deprecation")
     public static ArrayList<User> getAllRecords(String email){
        ArrayList<User> arrayList = new ArrayList<>();
        try{
            String query = "select * from user WHERE email LIKE ?";
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + email + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setMobileNumber(rs.getString("mobileNumber"));
                user.setAddress(rs.getString("address"));
                user.setSecurityQuestion(rs.getString("securityQuestion"));
                user.setStatus(rs.getString("status"));
                arrayList.add(user);
            }
            rs.close();
            ps.close();
            con.close();
        }
        catch(Exception e){
         JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
         return arrayList;
    } 
    
    @SuppressWarnings("deprecation")
    public static void changeStatus(String email,String status){
        String query = "update user set status=? where email=?";
        try {
            Connection con = ConnectionProvider.getCon();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, status);
            ps.setString(2, email);
            ps.executeUpdate();
            ps.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Status Changed Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
