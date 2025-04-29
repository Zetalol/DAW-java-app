package com.example.llibreria.llibres;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

/**
 * Servlet encarregat d'eliminar un llibre i les seves relacions.
 *
 * Esborra primer les relacions a les taules intermitges i, a continuació,
 * elimina el llibre principalment.
 */
@WebServlet("/DeleteLlibre")
public class DeleteLlibre extends HttpServlet {

    /**
     * Processa la petició POST per eliminar un llibre.
     *
     * @param request  Petició HTTP amb l'ID del llibre.
     * @param response Resposta HTTP.
     * @throws ServletException Si hi ha un error amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada o sortida.
     */
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
