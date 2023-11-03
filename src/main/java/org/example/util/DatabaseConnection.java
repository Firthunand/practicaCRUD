package org.example.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String url="jdbc:mysql://localhost:3306/cursojavasesqlybbdd";
    private static String user="root";
    private static String password="mona.1990";

    //private static Connection conn;
    private static BasicDataSource pool;

    ////CONECTANDO A LA BASE DE DATOS///////////////////////////////////////////
    public static /*Connection*/ BasicDataSource getInstance() throws SQLException {
        if (/*conn==null*/pool==null){
            //conn= DriverManager.getConnection(url,user,password);
            pool= new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(password);

            pool.setInitialSize(3);
            pool.setMinIdle(3);
            pool.setMaxIdle(10);
            pool.setMaxTotal(10);
            System.out.println("conexion establecida");
        }

        //return conn;
        return pool;
    }
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection();
    }
}
