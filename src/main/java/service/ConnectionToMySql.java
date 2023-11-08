package service;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionToMySql {
    private static String URL="jdbc:mysql://localhost:3306/testModule3";
    private static String USERNAME="root";
    private static String PASSWORD="123456";

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        }catch (Exception e){
            return null;
        }
    }
}
