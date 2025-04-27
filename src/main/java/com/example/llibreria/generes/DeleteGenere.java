package com.example.llibreria.generes;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/DeleteGenere")
public class DeleteGenere extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idGenereStr = request.getParameter("idGenere");
        int idGenere = Integer.parseInt(idGenereStr);

        String sqlRelacions = "DELETE FROM llibre_genere WHERE id_genere=?";
        String sqlGenere = "DELETE FROM generes WHERE id=?";

        try (Connection con = Connexio.getConnection()) {
            con.setAutoCommit(false);

            // Eliminar relacions
            try (PreparedStatement psRel = con.prepareStatement(sqlRelacions)) {
                psRel.setInt(1, idGenere);
                psRel.executeUpdate();
            }

            // Eliminar el gènere
            try (PreparedStatement psGen = con.prepareStatement(sqlGenere)) {
                psGen.setInt(1, idGenere);
                psGen.executeUpdate();
            }

            con.commit();
            response.sendRedirect("ListGeneres");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
