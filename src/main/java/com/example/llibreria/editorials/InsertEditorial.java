package com.example.llibreria.editorials;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet encarregat d'inserir una nova editorial a la base de dades.
 * 
 * Rep les dades del formulari i crea un nou registre a la taula editorials.
 */
@WebServlet("/InsertEditorial")
public class InsertEditorial extends HttpServlet {

    /**
     * Processa les peticions POST per inserir una nova editorial.
     *
     * @param request  Objecte HttpServletRequest amb el nom de l'editorial.
     * @param response Objecte HttpServletResponse per redirigir després de la
     *                 inserció.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
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
