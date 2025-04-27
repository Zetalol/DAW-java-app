package com.example.llibreria.generes;

import com.example.llibreria.Connexio;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/UpdateGenere")
public class UpdateGenere extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idGenereStr = request.getParameter("idGenere");
        String nomGenere = request.getParameter("nomGenere");
        int idGenere = Integer.parseInt(idGenereStr);

        String sql = "UPDATE generes SET nom=? WHERE id=?";

        try (Connection con = Connexio.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nomGenere);
            ps.setInt(2, idGenere);
            ps.executeUpdate();
            response.sendRedirect("ListGeneres");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
