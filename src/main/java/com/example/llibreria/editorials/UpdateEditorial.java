package com.example.llibreria.editorials;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet encarregat d'actualitzar la informació d'una editorial existent.
 * 
 * Actualitza el nom de l'editorial a partir de l'identificador rebut.
 */
@WebServlet("/UpdateEditorial")
public class UpdateEditorial extends HttpServlet {
    /**
     * Processa les peticions POST per actualitzar una editorial.
     *
     * @param request  Objecte HttpServletRequest amb l'id i el nou nom de
     *                 l'editorial.
     * @param response Objecte HttpServletResponse per redirigir després de
     *                 l'actualització.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEditorial = Integer.parseInt(request.getParameter("idEditorial"));
        String nomEditorial = request.getParameter("nomEditorial");

        String sql = "UPDATE editorials SET nom=? WHERE id=?";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nomEditorial);
            ps.setInt(2, idEditorial);
            ps.executeUpdate();
            response.sendRedirect("ListEditorials");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
