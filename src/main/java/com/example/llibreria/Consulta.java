package com.example.llibreria;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/consulta")
public class Consulta extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><head><title>Consulta de Llibres</title></head><body>");
            out.println("<h1>Llistat de Llibres</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Títol</th><th>ISBN</th><th>Any de publicació</th></tr>");

            try (Connection conn = Connexio.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT titol, isbn, any_publicacio FROM llibres")) {

                while (rs.next()) {
                    out.println("<tr>");
                    out.println("<td>" + rs.getString("titol") + "</td>");
                    out.println("<td>" + rs.getString("isbn") + "</td>");
                    out.println("<td>" + rs.getInt("any_publicacio") + "</td>");
                    out.println("</tr>");
                }
            } catch (Exception e) {
                out.println("<tr><td colspan='3'>Error: " + e.getMessage() + "</td></tr>");
                e.printStackTrace();
            }
            out.println("</table>");
            out.println("</body></html>");
        }
    }
}


