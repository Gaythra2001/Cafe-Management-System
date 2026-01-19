/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import model.Bill;


/**
 *
 * @author PIPUNI
 */
public class BillDao {
    
    public static String getId(){
        int id = 1;
        try{
            ResultSet rs =DbOperations.getData("select IFNULL(max(id), 0) as max_id from bill");
            if(rs != null){
                if(rs.next()){
                    id = rs.getInt("max_id");
                    id = id + 1;
                }
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            id = 1;
        }
        return String.valueOf(id);
    }
    
    public static void save(Bill bill){
        String query = "insert into bill values('"+bill.getId()+"','"+bill.getName()+"','"+bill.getMobileNumber()+"','"+bill.getEmail()+"','"+bill.getDate()+"','"+bill.getTotal()+"','"+bill.getCreatedBy()+"')";
        DbOperations.setDataOrDelete(query, "Bill Details Added Successfully");
    }
    
    
    public static ArrayList<Bill> getAllRecordsByInc(String date){
        ArrayList<Bill> arrayList = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = ConnectionProvider.getCon();
            st = con.createStatement();
            rs = st.executeQuery("select * from bill where date like '%"+date+"%' order by id ASC");
            while(rs.next()){
                try{
                    Bill bill = new Bill();
                    bill.setId(rs.getInt("id"));
                    bill.setName(rs.getString("name"));
                    bill.setMobileNumber(rs.getString("mobileNumber"));
                    bill.setEmail(rs.getString("email"));
                    bill.setDate(rs.getString("date"));
                    bill.setTotal(rs.getString("total"));
                    bill.setCreatedBy(rs.getString("createdBy"));
                    arrayList.add(bill);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error reading bill record: " + e);
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Query Error: " + e);
        }
        return arrayList;
    }
    
    
    
    public static ArrayList<Bill> getAllRecordsByDesc(String date){
        ArrayList<Bill> arrayList = new ArrayList<>();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try{
            con = ConnectionProvider.getCon();
            st = con.createStatement();
            rs = st.executeQuery("select * from bill where date like '%"+date+"%' order by id DESC");
            while(rs.next()){
                try{
                    Bill bill = new Bill();
                    bill.setId(rs.getInt("id"));
                    bill.setName(rs.getString("name"));
                    bill.setMobileNumber(rs.getString("mobileNumber"));
                    bill.setEmail(rs.getString("email"));
                    bill.setDate(rs.getString("date"));
                    bill.setTotal(rs.getString("total"));
                    bill.setCreatedBy(rs.getString("createdBy"));
                    arrayList.add(bill);
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error reading bill record: " + e);
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Query Error: " + e);
        }
        return arrayList;
    }
    
    
}
