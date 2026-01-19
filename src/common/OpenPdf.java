/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.awt.Desktop;
import java.io.File;
import javax.swing.JOptionPane;

/**
 *
 * @author PIPUNI
 */
public class OpenPdf {
    
    public static void openById(String id){
        try{
            String downloadsPath = "C:\\Users\\User\\Downloads\\";
            File file = new File(downloadsPath + id + ".pdf");
            if(file.exists()){
                Desktop.getDesktop().open(file);
            }
            else
            JOptionPane.showMessageDialog(null, "File is not Exists");    
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
