package com.example.llibreria.generes;

import com.example.llibreria.Connexio;
import com.example.llibreria.models.Genere;

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
 * Servlet encarregat de llistar tots els gèneres disponibles a la base de
 * dades.
 * 
 * Recupera la llista completa i la passa al JSP per a la seva visualització.
 */
@WebServlet("/ListGeneres")
public class ListGeneres extends HttpServlet {

    /**
     * Processa les peticions GET per obtenir tots els gèneres.
     *
     * @param request  Objecte HttpServletRequest per obtenir la sol·licitud del
     *                 client.
     * @param response Objecte HttpServletResponse per enviar la resposta al client.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Genere> llistaGeneres = new ArrayList<>();
        String sql = "SELECT * FROM generes";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Genere genere = new Genere();
                genere.setId(rs.getInt("id"));
                genere.setNom(rs.getString("nom"));
                llistaGeneres.add(genere);
            }

            request.setAttribute("generes", llistaGeneres);
            request.getRequestDispatcher("llistaGeneres.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
