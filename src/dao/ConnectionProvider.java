/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.File;
/**
 *
 * @author HP
 */
public class ConnectionProvider {

    public static Connection getCon() {
        try {
            Class.forName("org.sqlite.JDBC");
            
            String dbPath = System.getProperty("user.home") + File.separator + "cafe_management_system.db";
            // Enable WAL mode and timeouts for better SQLite concurrency
            String url = "jdbc:sqlite:" + dbPath;
            Connection con = DriverManager.getConnection(url);
            
            // Set pragmas for better performance and concurrency
            Statement stmt = con.createStatement();
            stmt.execute("PRAGMA journal_mode=WAL");
            stmt.execute("PRAGMA synchronous=NORMAL");
            stmt.execute("PRAGMA cache_size=10000");
            stmt.execute("PRAGMA foreign_keys=ON");
            stmt.execute("PRAGMA busy_timeout=5000");
            stmt.close();
            
            return con;

        } catch (Exception e) {
            e.printStackTrace(); // shows real error if any
            return null;
        }
    }
}








