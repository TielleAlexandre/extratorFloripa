package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBDFloripa {
	

    public Connection getConnection() {

        Connection conn = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dados_floripa?useSSL=false", "root", "123mudar");
          
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }


}
