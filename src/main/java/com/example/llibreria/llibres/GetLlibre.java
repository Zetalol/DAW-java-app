package com.example.llibreria.llibres;

import com.example.llibreria.Connexio;
import com.example.llibreria.models.Autor;
import com.example.llibreria.models.Editorial;
import com.example.llibreria.models.Genere;
import com.example.llibreria.models.Llibre;

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
 * Servlet encarregat de carregar un llibre i les seves relacions
 * per mostrar-les en un formulari.
 */
@WebServlet("/GetLlibre")
public class GetLlibre extends HttpServlet {
    /**
     * Processa la petició GET per obtenir les dades d'un llibre existent.
     *
     * @param request  Petició HTTP amb l'ID del llibre.
     * @param response Resposta HTTP.
     * @throws ServletException Si hi ha un error amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada o sortida.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idLlibreStr = request.getParameter("id");
        Llibre llibre = null;
        List<Autor> autors = new ArrayList<>();
        List<Genere> generes = new ArrayList<>();
        List<Editorial> editorials = new ArrayList<>();

        try (Connection con = Connexio.getConnection()) {
            // Carregar llistes d'autors, gèneres i editorials
            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM autors");
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Autor autor = new Autor();
                    autor.setId(rs.getInt("id"));
                    autor.setNom(rs.getString("nom"));
                    autors.add(autor);
                }
            }

            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM generes");
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Genere genere = new Genere();
                    genere.setId(rs.getInt("id"));
                    genere.setNom(rs.getString("nom"));
                    generes.add(genere);
                }
            }

            try (PreparedStatement ps = con.prepareStatement("SELECT * FROM editorials");
                    ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Editorial editorial = new Editorial();
                    editorial.setId(rs.getInt("id"));
                    editorial.setNom(rs.getString("nom"));
                    editorials.add(editorial);
                }
            }

            // Si hi ha un id, carregar dades del llibre
            if (idLlibreStr != null) {
                int idLlibre = Integer.parseInt(idLlibreStr);
                String sqlLlibre = "SELECT * FROM llibres WHERE id = ?";
                try (PreparedStatement ps = con.prepareStatement(sqlLlibre)) {
                    ps.setInt(1, idLlibre);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        llibre = new Llibre();
                        llibre.setId(rs.getInt("id"));
                        llibre.setTitol(rs.getString("titol"));
                        llibre.setIsbn(rs.getString("isbn"));
                        llibre.setAnyPublicacio(rs.getInt("any_publicacio"));
                        llibre.setIdEditorial(rs.getInt("id_editorial"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Passar dades al JSP
        request.setAttribute("llibre", llibre);
        request.setAttribute("autors", autors);
        request.setAttribute("generes", generes);
        request.setAttribute("editorials", editorials);

        request.getRequestDispatcher("formLlibres.jsp").forward(request, response);
    }
}
