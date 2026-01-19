/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class Tables {

    public static void main(String[] args) {
        try {
            String userTable = "Create table if not exists user(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(200),email varchar(200),mobileNumber varchar(10),address varchar(200),password varchar(200),securityQuestion varchar(200),answer varchar(200),status varchar(200),UNIQUE (email))";
            String adminDetails = "insert or ignore into user (name,email,mobileNumber, address, password,securityQuestion, answer, status) values('admin','admin@gmail.com','0711912872','Gampaha','nnkk','What is your name?','sally','true')";

            String categoryTable = "create table if not exists category(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(200))";
            String productTable = "create table if not exists product(id INTEGER PRIMARY KEY AUTOINCREMENT,name varchar(200),category varchar(200), price varchar(200))";
            String billTable = "create table if not exists bill(id INTEGER PRIMARY KEY,name varchar(200),mobileNumber varchar(200),email varchar(200),date varchar(50),total varchar(200),createdBy varchar(200) )";
            DbOperations.setDataOrDelete(userTable, "User Table Created Successfully");
            DbOperations.setDataOrDelete(adminDetails, "Admin details added Successfully");
            DbOperations.setDataOrDelete(categoryTable, "Category Table Created Successfully");
            DbOperations.setDataOrDelete(productTable, "Product Table Created Successfully");
            DbOperations.setDataOrDelete(billTable, "bill Table Created Successfully");
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }
}
