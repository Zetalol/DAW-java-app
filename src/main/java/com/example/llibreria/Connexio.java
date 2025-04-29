package com.example.llibreria;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe utilitària per gestionar la connexió amb la base de dades.
 *
 * Obté un `DataSource` configurat al servidor d'aplicacions i proporciona
 * connexions JDBC.
 */
public class Connexio {
    private static DataSource dataSource;

    static {
        try {
            InitialContext ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:/LlibresDS");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna una connexió activa a la base de dades.
     *
     * @return Connexió JDBC activa.
     * @throws SQLException Si no es pot obtenir la connexió.
     */
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("No es pot obtenir el DataSource");
        }
        return dataSource.getConnection();
    }
}
