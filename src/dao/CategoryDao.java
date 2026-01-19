/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.category;
import java.sql.*;

/**
 *
 * @author PIPUNI
 */
public class CategoryDao {
    
    public static void save(category categor){
        String query = "insert into category(name) values('"+categor.getName()+"') ";
        DbOperations.setDataOrDelete(query,"Category Added Successfully");
    }
    
    public static ArrayList<category> getAllRecords(){
        ArrayList<category> arrayList = new ArrayList<>();
        try{
            ResultSet rs = DbOperations.getData("select *from category");
            while (rs.next()){
                category categor = new category();
                categor.setId(rs.getInt("id"));
                categor.setName(rs.getString("name"));
                arrayList.add(categor);
                
            }
        
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    
    public static void delete(String id){
        String query = "delete from category where id='"+id+"'";
        DbOperations.setDataOrDelete(query, "Category Deleted Successfully");
    }
    
}
