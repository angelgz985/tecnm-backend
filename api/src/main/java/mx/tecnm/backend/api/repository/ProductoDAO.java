package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.backend.api.models.Producto;

@Repository
public class ProductoDAO {

    @Autowired
    private JdbcClient jdbcClient;

    public List<Producto> obtenerProductos() {
        String sql = """
            SELECT id, nombre, precio, sku, color, marca, descripcion, 
                   peso, alto, ancho, profundidad, categorias_id 
            FROM productos
            """;
        return jdbcClient.sql(sql)
                .query(new ProductoRM())
                .list();
    }

    public Producto obtenerProductoPorId(int id) {
        String sql = """
            SELECT id, nombre, precio, sku, color, marca, descripcion, 
                   peso, alto, ancho, profundidad, categorias_id 
            FROM productos WHERE id = ?
            """;
        return jdbcClient.sql(sql)
                .param(id)
                .query(new ProductoRM())
                .single();
    }

    public Producto crearProducto(Producto producto) {
        String sql = """
            INSERT INTO productos (nombre, precio, sku, color, marca, descripcion, 
                                  peso, alto, ancho, profundidad, categorias_id) 
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) 
            RETURNING id, nombre, precio, sku, color, marca, descripcion, 
                      peso, alto, ancho, profundidad, categorias_id
            """;
        return jdbcClient.sql(sql)
                .params(
                    producto.nombre(),
                    producto.precio(),
                    producto.sku(),
                    producto.color(),
                    producto.marca(),
                    producto.descripcion(),
                    producto.peso(),
                    producto.alto(),
                    producto.ancho(),
                    producto.profundidad(),
                    producto.categoriasId()
                )
                .query(new ProductoRM())
                .single();
    }

    public Producto actualizarProducto(int id, Producto producto) {
        String sql = """
            UPDATE productos 
            SET nombre = ?, precio = ?, sku = ?, color = ?, marca = ?, 
                descripcion = ?, peso = ?, alto = ?, ancho = ?, 
                profundidad = ?, categorias_id = ? 
            WHERE id = ? 
            RETURNING id, nombre, precio, sku, color, marca, descripcion, 
                      peso, alto, ancho, profundidad, categorias_id
            """;
        return jdbcClient.sql(sql)
                .params(
                    producto.nombre(),
                    producto.precio(),
                    producto.sku(),
                    producto.color(),
                    producto.marca(),
                    producto.descripcion(),
                    producto.peso(),
                    producto.alto(),
                    producto.ancho(),
                    producto.profundidad(),
                    producto.categoriasId(),
                    id
                )
                .query(new ProductoRM())
                .single();
    }

    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        jdbcClient.sql(sql)
                .param(id)
                .update();
    }

    public boolean existeProducto(int id) {
        String sql = "SELECT COUNT(*) FROM productos WHERE id = ?";
        Integer count = jdbcClient.sql(sql)
                .param(id)
                .query(Integer.class)
                .single();
        return count != null && count > 0;
    }

    public List<Producto> obtenerProductosPorCategoria(int categoriaId) {
        String sql = """
            SELECT id, nombre, precio, sku, color, marca, descripcion, 
                   peso, alto, ancho, profundidad, categorias_id 
            FROM productos WHERE categorias_id = ?
            """;
        return jdbcClient.sql(sql)
                .param(categoriaId)
                .query(new ProductoRM())
                .list();
    }
}