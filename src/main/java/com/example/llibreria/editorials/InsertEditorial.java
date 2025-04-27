package com.example.llibreria.editorials;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/InsertEditorial")
public class InsertEditorial extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomEditorial = request.getParameter("nomEditorial");

        String sql = "INSERT INTO editorials (nom) VALUES (?)";

        try (Connection con = Connexio.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nomEditorial);
            ps.executeUpdate();
            response.sendRedirect("ListEditorials");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
