package com.lottiefiles.dataBase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final Logger logger = LogManager.getLogger();

    public static List<User> getUsersFromDataBase() {
        String url = "jdbc:sqlite:/Users/maria/Documents/lotifiles.db";
        String sql = "SELECT * FROM Users";

        List<User> usersAllFromDB = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usersAllFromDB.add(new User(rs.getInt("UserID"), rs.getString("Email"), rs.getString("Password")));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return usersAllFromDB;
    }

    public static String getEmailUserFromDataBase(){
        List<User> users = getUsersFromDataBase();
        User user1 = users.get(0);
        return user1.name;
    }

    public static String getPasswordUserFromDataBase(){
        List<User> users = getUsersFromDataBase();
        User user1 = users.get(0);
        return user1.password;
    }
}


