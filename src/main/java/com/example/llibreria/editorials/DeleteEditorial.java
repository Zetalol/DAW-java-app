package com.example.llibreria.editorials;
import com.example.llibreria.Connexio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;



@WebServlet("/DeleteEditorial")
public class DeleteEditorial extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idEditorial = Integer.parseInt(request.getParameter("idEditorial"));

        String sql = "DELETE FROM editorials WHERE id=?";

        try (Connection con = Connexio.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idEditorial);
            ps.executeUpdate();
            response.sendRedirect("ListEditorials");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
