package com.example.llibreria.autors;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet que permet eliminar un autor de la base de dades.
 * 
 * Primer esborra les relacions existents a la taula intermediària
 * {@code llibre_autor} i després elimina l'autor de la taula {@code autors}.
 * 
 * La transacció es gestiona manualment per assegurar la integritat de les
 * dades.
 */
@WebServlet("/DeleteAutor")
public class DeleteAutor extends HttpServlet {

    /**
     * Processa les peticions POST per eliminar un autor de la base de dades.
     * 
     * @param request  Objecte HttpServletRequest que conté la petició del client.
     * @param response Objecte HttpServletResponse per enviar la resposta al client.
     * @throws ServletException Si hi ha un error relacionat amb el servlet.
     * @throws IOException      Si hi ha un error d'entrada/sortida.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperar l'ID de l'autor a eliminar
        String idAutorStr = request.getParameter("idAutor");
        int idAutor = Integer.parseInt(idAutorStr);

        // 2. Eliminar primer de la taula intermediària (llibre_autor) per trencar
        // relacions
        String sqlRelacions = "DELETE FROM llibre_autor WHERE id_autor=?";

        // 3. Després, eliminar de la taula 'autors'
        String sqlAutor = "DELETE FROM autors WHERE id=?";

        try (Connection con = Connexio.getConnection()) {
            // Es recomana transacció
            con.setAutoCommit(false);

            try (PreparedStatement psRel = con.prepareStatement(sqlRelacions)) {
                psRel.setInt(1, idAutor);
                psRel.executeUpdate();
            }

            try (PreparedStatement psAutor = con.prepareStatement(sqlAutor)) {
                psAutor.setInt(1, idAutor);
                psAutor.executeUpdate();
            }

            con.commit();
            // 4. Redirigir a la pàgina de llista d’autors
            response.sendRedirect("ListAutors");

        } catch (SQLException e) {
            e.printStackTrace();
            // Possible rollback
            response.sendRedirect("error.jsp");
        }
    }
}
