package com.example.llibreria.generes;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet encarregat d'eliminar un gènere de la base de dades.
 * 
 * Primer elimina les relacions associades i després elimina el gènere
 * principal.
 */
@WebServlet("/DeleteGenere")
public class DeleteGenere extends HttpServlet {

    /**
     * Processa les peticions POST per eliminar un gènere.
     *
     * @param request  Objecte HttpServletRequest amb l'id del gènere.
     * @param response Objecte HttpServletResponse per redirigir després de
     *                 l'eliminació.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idGenereStr = request.getParameter("idGenere");
        int idGenere = Integer.parseInt(idGenereStr);

        String sqlRelacions = "DELETE FROM llibre_genere WHERE id_genere=?";
        String sqlGenere = "DELETE FROM generes WHERE id=?";

        try (Connection con = Connexio.getConnection()) {
            con.setAutoCommit(false);

            // Eliminar relacions
            try (PreparedStatement psRel = con.prepareStatement(sqlRelacions)) {
                psRel.setInt(1, idGenere);
                psRel.executeUpdate();
            }

            // Eliminar el gènere
            try (PreparedStatement psGen = con.prepareStatement(sqlGenere)) {
                psGen.setInt(1, idGenere);
                psGen.executeUpdate();
            }

            con.commit();
            response.sendRedirect("ListGeneres");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
