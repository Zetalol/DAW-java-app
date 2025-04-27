package com.example.llibreria.llibres;

import com.example.llibreria.Connexio;
import com.example.llibreria.models.Editorial;
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

@WebServlet("/ListLlibres")
public class ListLlibres extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Llibre> llistaLlibres = new ArrayList<>();
        List<Editorial> llistaEditorials = new ArrayList<>();

        try (Connection con = Connexio.getConnection()) {
            // Carregar llibres
            String sqlLlibres = "SELECT\n" +
                    "    llibres.id AS id_llibre,\n" +
                    "    llibres.titol,\n" +
                    "    llibres.isbn,\n" +
                    "    llibres.any_publicacio,\n" +
                    "    editorials.nom AS editorial,\n" +
                    "    autors.nom AS autor,\n" +
                    "    generes.nom AS genere\n" +
                    "FROM llibres\n" +
                    "LEFT JOIN editorials ON llibres.id_editorial = editorials.id\n" +
                    "LEFT JOIN llibre_autor ON llibres.id = llibre_autor.id_llibre\n" +
                    "LEFT JOIN autors ON llibre_autor.id_autor = autors.id\n" +
                    "LEFT JOIN llibre_genere ON llibres.id = llibre_genere.id_llibre\n" +
                    "LEFT JOIN generes ON llibre_genere.id_genere = generes.id\n" +
                    "ORDER BY llibres.id;";
            try (PreparedStatement ps = con.prepareStatement(sqlLlibres)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Llibre llibre = new Llibre();
                    llibre.setId(rs.getInt("id_llibre"));
                    llibre.setTitol(rs.getString("titol"));
                    llibre.setIsbn(rs.getString("isbn"));
                    llibre.setAnyPublicacio(rs.getInt("any_publicacio"));
                    llibre.setEditorialNom(rs.getString("editorial"));
                    llibre.setAutorNom(rs.getString("autor"));
                    llibre.setGenereNom(rs.getString("genere"));
                    llistaLlibres.add(llibre);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("llibres", llistaLlibres);
        request.getRequestDispatcher("llistaLlibres.jsp").forward(request, response);
    }
}
