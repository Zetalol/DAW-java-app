package com.example.llibreria.autors;

import com.example.llibreria.Connexio;
import com.example.llibreria.models.Autor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/GetAutor")
public class GetAutor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idAutorStr = request.getParameter("id");
        Autor autor = null;

        if (idAutorStr != null && !idAutorStr.isEmpty()) {
            int idAutor = Integer.parseInt(idAutorStr);

            String sql = "SELECT * FROM autors WHERE id=?";
            try (Connection con = Connexio.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, idAutor);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    autor = new Autor();
                    autor.setId(rs.getInt("id"));
                    autor.setNom(rs.getString("nom"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Enviar l'autor al JSP
        request.setAttribute("autor", autor);
        request.getRequestDispatcher("formAutor.jsp").forward(request, response);
    }
}
