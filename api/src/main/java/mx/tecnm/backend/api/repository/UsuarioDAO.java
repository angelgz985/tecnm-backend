package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.backend.api.models.Usuario;

@Repository
public class UsuarioDAO {

    @Autowired
    private JdbcClient jdbcClient;

    public List<Usuario> obtenerUsuarios() {
        String sql = "SELECT id, nombre, email, password FROM usuarios";
        return jdbcClient.sql(sql)
                .query(new UsuarioRM())
                .list();
    }

    public Usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT id, nombre, email, password FROM usuarios WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(id)
                .query(new UsuarioRM())
                .single();
    }

    public Usuario obtenerUsuarioPorEmail(String email) {
        String sql = "SELECT id, nombre, email, password FROM usuarios WHERE email = ?";
        return jdbcClient.sql(sql)
                .param(email)
                .query(new UsuarioRM())
                .single();
    }

    public Usuario crearUsuario(Usuario usuario) {
        String sql = """
            INSERT INTO usuarios (nombre, email, password) 
            VALUES (?, ?, ?) 
            RETURNING id, nombre, email, password
            """;
        return jdbcClient.sql(sql)
                .params(
                    usuario.nombre(),
                    usuario.email(),
                    usuario.password()
                )
                .query(new UsuarioRM())
                .single();
    }

    public Usuario actualizarUsuario(int id, Usuario usuario) {
        String sql = """
            UPDATE usuarios 
            SET nombre = ?, email = ?, password = ? 
            WHERE id = ? 
            RETURNING id, nombre, email, password
            """;
        return jdbcClient.sql(sql)
                .params(
                    usuario.nombre(),
                    usuario.email(),
                    usuario.password(),
                    id
                )
                .query(new UsuarioRM())
                .single();
    }

    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        jdbcClient.sql(sql)
                .param(id)
                .update();
    }
}