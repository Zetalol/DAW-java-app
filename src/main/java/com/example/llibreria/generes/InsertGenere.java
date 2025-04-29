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
 * Servlet encarregat d'inserir un nou gènere a la base de dades.
 * 
 * Rep les dades del formulari i crea un nou registre a la taula de generes.
 */
@WebServlet("/InsertGenere")
public class InsertGenere extends HttpServlet {

    /**
     * Processa les peticions POST per inserir un nou gènere.
     *
     * @param request  Objecte HttpServletRequest amb el nom del gènere.
     * @param response Objecte HttpServletResponse per redirigir després de la
     *                 inserció.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomGenere = request.getParameter("nomGenere");
        String sql = "INSERT INTO generes (nom) VALUES (?)";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomGenere);
            ps.executeUpdate();
            response.sendRedirect("ListGeneres");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
