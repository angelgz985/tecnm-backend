package mx.tecnm.backend.api.repository;

import org.springframework.jdbc.core.RowMapper;
import mx.tecnm.backend.api.models.Usuario;

public class UsuarioRM implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}