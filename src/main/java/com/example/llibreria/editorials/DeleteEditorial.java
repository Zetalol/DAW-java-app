package com.example.llibreria.editorials;

import com.example.llibreria.Connexio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Servlet encarregat d'eliminar una editorial de la base de dades.
 * 
 * Rep l'identificador de l'editorial i executa la seva eliminació.
 */
@WebServlet("/DeleteEditorial")
public class DeleteEditorial extends HttpServlet {

    /**
     * Processa les peticions POST per eliminar una editorial.
     *
     * @param request  Objecte HttpServletRequest amb l'identificador de
     *                 l'editorial.
     * @param response Objecte HttpServletResponse per redirigir després de
     *                 l'eliminació.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEditorial = Integer.parseInt(request.getParameter("idEditorial"));

        String sql = "DELETE FROM editorials WHERE id=?";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEditorial);
            ps.executeUpdate();
            response.sendRedirect("ListEditorials");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
