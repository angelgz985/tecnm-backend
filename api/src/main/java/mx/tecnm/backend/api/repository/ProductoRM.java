package mx.tecnm.backend.api.repository;

import org.springframework.jdbc.core.RowMapper;
import mx.tecnm.backend.api.models.Producto;

public class ProductoRM implements RowMapper<Producto> {
    @Override
    public Producto mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        return new Producto(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getDouble("precio"),
            rs.getString("sku"),
            rs.getString("color"),
            rs.getString("marca"),
            rs.getString("descripcion"),
            rs.getDouble("peso"),
            rs.getDouble("alto"),
            rs.getDouble("ancho"),
            rs.getDouble("profundidad"),
            rs.getInt("categorias_id")
        );
    }
}