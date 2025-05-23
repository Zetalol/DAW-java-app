package com.example.llibreria.editorials;

import com.example.llibreria.Connexio;
import com.example.llibreria.models.Editorial;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet encarregat d'obtenir la informació d'una editorial específica.
 * 
 * Cerca l'editorial a la base de dades i la prepara per ser editada o
 * visualitzada.
 */
@WebServlet("/GetEditorial")
public class GetEditorial extends HttpServlet {

    /**
     * Processa les peticions GET per recuperar una editorial concreta.
     *
     * @param request  Objecte HttpServletRequest amb l'identificador de
     *                 l'editorial.
     * @param response Objecte HttpServletResponse per enviar la informació a la
     *                 vista JSP.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEditorial = Integer.parseInt(request.getParameter("id"));

        Editorial editorial = null;

        String sql = "SELECT * FROM editorials WHERE id=?";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEditorial);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                editorial = new Editorial();
                editorial.setId(rs.getInt("id"));
                editorial.setNom(rs.getString("nom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("editorial", editorial);
        request.getRequestDispatcher("formEditorial.jsp").forward(request, response);
    }
}
