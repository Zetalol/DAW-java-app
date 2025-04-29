package com.example.llibreria.editorials;

import com.example.llibreria.models.Editorial;
import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet encarregat de llistar totes les editorials de la base de dades.
 * 
 * Recupera els registres de la taula editorials i els envia al JSP
 * corresponent.
 */
@WebServlet("/ListEditorials")
public class ListEditorials extends HttpServlet {

    /**
     * Processa les peticions GET per obtenir i mostrar la llista d'editorials.
     *
     * @param request  Objecte HttpServletRequest utilitzat per enviar la llista.
     * @param response Objecte HttpServletResponse per redirigir cap al JSP.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Editorial> llistaEditorials = new ArrayList<>();

        String sql = "SELECT * FROM editorials";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Editorial editorial = new Editorial();
                editorial.setId(rs.getInt("id"));
                editorial.setNom(rs.getString("nom"));
                llistaEditorials.add(editorial);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("editorials", llistaEditorials);
        request.getRequestDispatcher("llistaEditorials.jsp").forward(request, response);
    }
}
