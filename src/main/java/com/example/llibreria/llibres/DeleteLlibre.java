package com.example.llibreria.llibres;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/DeleteLlibre")
public class DeleteLlibre extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idLlibreStr = request.getParameter("idLlibre");
        int idLlibre = Integer.parseInt(idLlibreStr);

        // 1. Eliminar relacions a llibre_autor i llibre_genere
        String sqlDeleteAutors = "DELETE FROM llibre_autor WHERE id_llibre=?";
        String sqlDeleteGeneres = "DELETE FROM llibre_genere WHERE id_llibre=?";
        // 2. Eliminar el llibre
        String sqlDeleteLlibre = "DELETE FROM llibres WHERE id=?";

        try (Connection con = Connexio.getConnection()) {
            con.setAutoCommit(false);

            // Eliminar relacions
            try (PreparedStatement psDelAutors = con.prepareStatement(sqlDeleteAutors)) {
                psDelAutors.setInt(1, idLlibre);
                psDelAutors.executeUpdate();
            }
            try (PreparedStatement psDelGeneres = con.prepareStatement(sqlDeleteGeneres)) {
                psDelGeneres.setInt(1, idLlibre);
                psDelGeneres.executeUpdate();
            }

            // Eliminar el llibre
            try (PreparedStatement psDelLlibre = con.prepareStatement(sqlDeleteLlibre)) {
                psDelLlibre.setInt(1, idLlibre);
                psDelLlibre.executeUpdate();
            }

            con.commit();
            response.sendRedirect("ListLlibres");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
