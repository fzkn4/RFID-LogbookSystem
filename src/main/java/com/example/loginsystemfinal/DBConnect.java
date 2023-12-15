package com.example.loginsystemfinal;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {
    public Connection dblink;

    public Connection getConnection (){
        String DB_user = "root";
        String DB_password = "shitload";
        String url = "jdbc:mysql://localhost:3306/logbookSystem";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dblink = DriverManager.getConnection(url,DB_user, DB_password);
        }catch (Exception e ) {
            e.printStackTrace();
        }
        return dblink;
    }
}

