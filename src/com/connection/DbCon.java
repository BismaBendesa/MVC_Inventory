/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Bisma
 */
public class DbCon {
    private static Connection con ; 
    public static Connection getcon(){
        if(con == null){
            try{
                System.out.println("connection succeed");
                String url = "jdbc:mysql://localhost/inventory"; // nama database
                String username = "root";
                String password = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                con = DriverManager.getConnection(url, username, password);
            } catch (Exception e){
                System.out.println(e);
            }
        }
        return con;
    }
}
