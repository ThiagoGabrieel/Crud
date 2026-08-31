package com.thiago.repository;

import com.thiago.connection.ConnectionFactory;
import com.thiago.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRepository {

    // Implementação para salvar o usuário no banco de dados
    public void salvar(Usuario usuario) {
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuario(nome,email,senha) VALUES (?,?,?)")){

                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getSenha());
                stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Implementação para buscar o usuário pelo id
    public Usuario buscarPorId(long id){
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE id = ?")){

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Implementação para buscar o usuário pelo email
    public Usuario buscarPorEmail(String email){
        try (Connection conn = ConnectionFactory.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuario WHERE email = ?")){

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Usuario usuario = new Usuario(
                        rs.getLong("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
                return usuario;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    // Implementação para verificar se o email já existe no banco de dados
    public Boolean emailJaExistente (String email){
        if(buscarPorEmail(email) != null){
            return true;
        }
        return false;
    }

    public boolean atualizarEmail(long id, String email){
        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement("UPDATE usuario SET email = ? WHERE id = ?")){

            stmt.setString(1, email);
            stmt.setLong(2, id);

            if(stmt.executeUpdate() == 1){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean atualizarSenha(long id, String senha){
        try(Connection conn = ConnectionFactory.conectar();
            PreparedStatement stmt = conn.prepareStatement("UPDATE usuario SET senha = ? WHERE id = ?")){

            stmt.setString(1, senha);
            stmt.setLong(2, id);

            if(stmt.executeUpdate() == 1){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
