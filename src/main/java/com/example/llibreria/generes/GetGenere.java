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

/**
 * Servlet encarregat d'obtenir la informació d'un gènere per la seva edició o
 * visualització.
 * 
 * Carrega un gènere concret a partir del seu identificador.
 */
@WebServlet("/GetGenere")
public class GetGenere extends HttpServlet {

    /**
     * Processa les peticions GET per obtenir un gènere específic.
     *
     * @param request  Objecte HttpServletRequest amb l'id del gènere.
     * @param response Objecte HttpServletResponse per enviar la informació al JSP
     *                 corresponent.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idGenereStr = request.getParameter("id");
        int idGenere = Integer.parseInt(idGenereStr);
        String sql = "SELECT * FROM generes WHERE id = ?";

        try (Connection con = Connexio.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idGenere);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Genere genere = new Genere();
                genere.setId(rs.getInt("id"));
                genere.setNom(rs.getString("nom"));
                request.setAttribute("genere", genere);
            }

            request.getRequestDispatcher("formGenere.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
