package com.thiago.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection conectar(){
        try {
            return DriverManager.getConnection(
            DbConfig.getUrl(),
            DbConfig.getUser(),
            DbConfig.getPassword()
            );

        } catch (SQLException e) {
            throw new RuntimeException("Driver do MySQL não encontrado", e);
        }
    }
}
