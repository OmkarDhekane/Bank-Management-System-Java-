package src;

import java.sql.*;

public class MAIN extends Thread {
    public static Connection connection;

    public void run() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(("jdbc:mysql://localhost:3306/bms"), "root",
                    "12omkar01Math");
            System.out.println("Connection created with database 'bms' ");
            new LoginPage();

        } catch (Exception e) {
            System.out.println("ERROR DURING CONNECTION TO DATABASE!");
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // new BBTransfer().BBTransferUI(1, "omkar");
        new MAIN().start();
    }
}
