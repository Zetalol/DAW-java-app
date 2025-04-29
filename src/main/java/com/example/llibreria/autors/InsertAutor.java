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
 * Servlet encarregat d'inserir un nou autor a la base de dades.
 * 
 * Rep les dades del formulari i les desa a la taula {@code autors}.
 */
@WebServlet("/nouAutor")
public class InsertAutor extends HttpServlet {

    /**
     * Processa les peticions POST per inserir un nou autor.
     *
     * @param request  Objecte HttpServletRequest amb les dades del formulari.
     * @param response Objecte HttpServletResponse per redirigir després de la
     *                 inserció.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperar paràmetres del formulari
        String nomAutor = request.getParameter("nomAutor");

        // 2. Inserir a la taula 'autors'
        String sql = "INSERT INTO autors (nom) VALUES (?)";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomAutor);
            ps.executeUpdate();

            // 3. Redirigir o fer forward a la pàgina de llista d’autors o confirmació
            response.sendRedirect("ListAutors");

        } catch (SQLException e) {
            e.printStackTrace();
            // Possiblement redirigir a un error.jsp
            response.sendRedirect("error.jsp");
        }
    }
}
