/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistempelayananpuskeswan;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author WINDOWS 10
 */
public class Database {

    public static Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // <- Ganti ini
            String url = "jdbc:mysql://192.168.1.1/puskeswan1?serverTimezone=Asia/Jakarta";
            String user = "root";
            String password = "";
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
