package com.example.llibreria.autors;

import com.example.llibreria.models.Autor;
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
 * Servlet encarregat d'obtenir i mostrar la llista d'autors de la base de
 * dades.
 * 
 * Llegeix tots els registres de la taula {@code autors} i els envia a la vista
 * JSP.
 */
@WebServlet(name = "ListAutors")
public class ListAutors extends HttpServlet {
    /**
     * Processa les peticions GET per obtenir i llistar tots els autors existents.
     *
     * @param request  Objecte HttpServletRequest per rebre la sol·licitud del
     *                 client.
     * @param response Objecte HttpServletResponse per enviar la resposta al client.
     * @throws ServletException Si ocorre un error de servlet.
     * @throws IOException      Si ocorre un error d'entrada/sortida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Autor> llistaAutors = new ArrayList<>();
        try (Connection conn = Connexio.getConnection()) {
            String sql = "SELECT * FROM autors";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Autor autor = new Autor();
                autor.setId(rs.getInt("id"));
                autor.setNom(rs.getString("nom"));
                llistaAutors.add(autor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Assignar la llista d'autors a l'atribut "autors"
        request.setAttribute("autors", llistaAutors);

        // Enviar a l'JSP
        request.getRequestDispatcher("llistaAutors.jsp").forward(request, response);
    }
}
