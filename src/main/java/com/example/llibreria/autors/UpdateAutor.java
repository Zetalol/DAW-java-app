package com.example.llibreria.autors;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet encarregat d'actualitzar la informació d'un autor existent.
 * 
 * Rep les dades modificades i realitza l'actualització a la base de dades.
 */
@WebServlet("/UpdateAutor")
public class UpdateAutor extends HttpServlet {

    /**
     * Processa les peticions POST per actualitzar un autor a la base de dades.
     *
     * @param request  Objecte HttpServletRequest amb les dades modificades.
     * @param response Objecte HttpServletResponse per redirigir després de
     *                 l'actualització.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
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
