package com.example.llibreria.llibres;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/UpdateLlibre")
public class UpdateLlibre extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperar paràmetres del formulari
        String idLlibreStr = request.getParameter("idLlibre");
        String titol = request.getParameter("titol");
        String isbn = request.getParameter("isbn");
        String anyStr = request.getParameter("any_publicacio");
        String idEditorialStr = request.getParameter("idEditorial");
        String idAutorStr = request.getParameter("idAutor");
        String idGenereStr = request.getParameter("idGenere");

        // Validació bàsica
        if (idLlibreStr == null || titol == null || isbn == null ||
                anyStr == null || idEditorialStr == null || idAutorStr == null || idGenereStr == null) {
            response.sendRedirect("error.jsp?message=Falten dades obligatòries");
            return;
        }

        int idLlibre = Integer.parseInt(idLlibreStr);
        int anyPublicacio = Integer.parseInt(anyStr);
        int idEditorial = Integer.parseInt(idEditorialStr);
        int idAutor = Integer.parseInt(idAutorStr);
        int idGenere = Integer.parseInt(idGenereStr);

        // 2. SQL statements
        String sqlUpdate = "UPDATE llibres SET titol=?, isbn=?, any_publicacio=?, id_editorial=? WHERE id=?";
        String sqlDeleteAutor = "DELETE FROM llibre_autor WHERE id_llibre=?";
        String sqlDeleteGenere = "DELETE FROM llibre_genere WHERE id_llibre=?";
        String sqlInsertAutor = "INSERT INTO llibre_autor (id_llibre, id_autor) VALUES (?, ?)";
        String sqlInsertGenere = "INSERT INTO llibre_genere (id_llibre, id_genere) VALUES (?, ?)";

        try (Connection con = Connexio.getConnection()) {
            con.setAutoCommit(false);

            // 2.1. Actualitzar dades bàsiques del llibre
            try (PreparedStatement psUpdate = con.prepareStatement(sqlUpdate)) {
                psUpdate.setString(1, titol);
                psUpdate.setString(2, isbn);
                psUpdate.setInt(3, anyPublicacio);
                psUpdate.setInt(4, idEditorial);
                psUpdate.setInt(5, idLlibre);
                psUpdate.executeUpdate();
            }

            // 2.2. Eliminar les relacions existents (autor i gènere)
            try (PreparedStatement psDelAutor = con.prepareStatement(sqlDeleteAutor)) {
                psDelAutor.setInt(1, idLlibre);
                psDelAutor.executeUpdate();
            }
            try (PreparedStatement psDelGenere = con.prepareStatement(sqlDeleteGenere)) {
                psDelGenere.setInt(1, idLlibre);
                psDelGenere.executeUpdate();
            }

            // 2.3. Inserir el nou autor associat
            try (PreparedStatement psInsAutor = con.prepareStatement(sqlInsertAutor)) {
                psInsAutor.setInt(1, idLlibre);
                psInsAutor.setInt(2, idAutor);
                psInsAutor.executeUpdate();
            }

            // 2.4. Inserir el nou gènere associat
            try (PreparedStatement psInsGenere = con.prepareStatement(sqlInsertGenere)) {
                psInsGenere.setInt(1, idLlibre);
                psInsGenere.setInt(2, idGenere);
                psInsGenere.executeUpdate();
            }

            // 3. Confirmar els canvis
            con.commit();

            // 4. Redirigir a la llista de llibres
            response.sendRedirect("ListLlibres");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error en actualitzar el llibre");
        }
    }
}
