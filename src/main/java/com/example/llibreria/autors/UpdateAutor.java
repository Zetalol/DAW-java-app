package com.example.llibreria.autors;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/UpdateAutor")
public class UpdateAutor extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperar paràmetres
        String idAutorStr = request.getParameter("idAutor");
        String nomAutor = request.getParameter("nomAutor");

        int idAutor = Integer.parseInt(idAutorStr);

        // 2. Executar UPDATE
        String sql = "UPDATE autors SET nom=? WHERE id=?";

        try (Connection con = Connexio.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomAutor);
            ps.setInt(2, idAutor);
            ps.executeUpdate();

            // 3. Redirigir a la pàgina de llista d’autors
            response.sendRedirect("ListAutors");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
