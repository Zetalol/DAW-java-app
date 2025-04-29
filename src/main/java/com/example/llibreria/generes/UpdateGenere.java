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
 * Servlet encarregat d'actualitzar la informació d'un gènere existent.
 * 
 * Rep les dades modificades a través d'un formulari i actualitza el registre
 * corresponent.
 */
@WebServlet("/UpdateGenere")
public class UpdateGenere extends HttpServlet {

    /**
     * Processa les peticions POST per modificar un gènere existent.
     *
     * @param request  Objecte HttpServletRequest amb les dades actualitzades del
     *                 gènere.
     * @param response Objecte HttpServletResponse per redirigir després de la
     *                 modificació.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idGenereStr = request.getParameter("idGenere");
        String nomGenere = request.getParameter("nomGenere");
        int idGenere = Integer.parseInt(idGenereStr);

        String sql = "UPDATE generes SET nom=? WHERE id=?";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomGenere);
            ps.setInt(2, idGenere);
            ps.executeUpdate();
            response.sendRedirect("ListGeneres");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
