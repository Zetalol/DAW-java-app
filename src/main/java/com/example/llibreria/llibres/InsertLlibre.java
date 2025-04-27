package com.example.llibreria.llibres;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/InsertLlibre")
public class InsertLlibre extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperar camps bàsics
        String titol = request.getParameter("titol");
        String isbn = request.getParameter("isbn");
        String anyStr = request.getParameter("any_publicacio");
        String idEditorialStr = request.getParameter("idEditorial");
        String idAutorStr = request.getParameter("idAutor");
        String idGenereStr = request.getParameter("idGenere");

        // Validació bàsica
        if (titol == null || titol.trim().isEmpty() || isbn == null || isbn.trim().isEmpty() ||
                anyStr == null || idEditorialStr == null || idAutorStr == null || idGenereStr == null) {
            response.sendRedirect("error.jsp?message=Falten dades obligatòries");
            return;
        }

        try (Connection con = Connexio.getConnection()) {
            con.setAutoCommit(false);

            int anyPublicacio = Integer.parseInt(anyStr);
            int idEditorial = Integer.parseInt(idEditorialStr);
            int idAutor = Integer.parseInt(idAutorStr);
            int idGenere = Integer.parseInt(idGenereStr);

            // 2. Insertar el llibre a la taula 'llibres' i recuperar l’ID generat
            String sqlInsertLlibre =
                    "INSERT INTO llibres (titol, isbn, any_publicacio, id_editorial) " +
                            "VALUES (?, ?, ?, ?)";
            int idNouLlibre;

            try (PreparedStatement psLlibre = con.prepareStatement(sqlInsertLlibre, Statement.RETURN_GENERATED_KEYS)) {
                psLlibre.setString(1, titol);
                psLlibre.setString(2, isbn);
                psLlibre.setInt(3, anyPublicacio);
                psLlibre.setInt(4, idEditorial);

                psLlibre.executeUpdate();

                // Recuperar l'ID generat
                try (ResultSet rs = psLlibre.getGeneratedKeys()) {
                    if (rs.next()) {
                        idNouLlibre = rs.getInt(1);
                    } else {
                        throw new SQLException("No s’ha pogut obtenir l’ID del llibre inserit!");
                    }
                }
            }

            // 3. Insertar l'autor a la taula 'llibre_autor'
            String sqlInsertAutor = "INSERT INTO llibre_autor (id_llibre, id_autor) VALUES (?, ?)";
            try (PreparedStatement psAutor = con.prepareStatement(sqlInsertAutor)) {
                psAutor.setInt(1, idNouLlibre);
                psAutor.setInt(2, idAutor);
                psAutor.executeUpdate();
            }

            // 4. Insertar el gènere a la taula 'llibre_genere'
            String sqlInsertGenere = "INSERT INTO llibre_genere (id_llibre, id_genere) VALUES (?, ?)";
            try (PreparedStatement psGenere = con.prepareStatement(sqlInsertGenere)) {
                psGenere.setInt(1, idNouLlibre);
                psGenere.setInt(2, idGenere);
                psGenere.executeUpdate();
            }

            // Confirma els canvis
            con.commit();

            // 5. Redirigir a la pàgina de llista
            response.sendRedirect("ListLlibres");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Format invàlid");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Error en inserir el llibre");
        }
    }
}
