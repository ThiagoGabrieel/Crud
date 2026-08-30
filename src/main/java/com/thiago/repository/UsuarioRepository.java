package com.thiago.repository;

import com.thiago.connection.ConnectionFactory;
import com.thiago.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioRepository {

    // Implementação para salvar o usuário no banco de dados
    public void salvar(Usuario usuario) {
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuario(nome,email,senha) VALUES (?,?,?)")){

                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
