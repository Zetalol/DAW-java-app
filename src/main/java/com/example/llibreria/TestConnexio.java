package com.example.llibreria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnexio {
    public static void main(String[] args) {
        try (Connection conn = Connexio.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM llibres")) {

            while (rs.next()) {
                System.out.println("Llibre: " + rs.getString("titol"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
