/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jframes;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Administrator
 */
public class DBConnection {
    
 static  Connection con =null;
   public static Connection getConnection(){
   
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://127.0.0.2:3306/easypcdb?serverTimezone=UTC&useSSL=false ","root","matthewgallardo");
        } catch (Exception e) {
            e.printStackTrace();
        }
    return con;
   }
         
        
}
