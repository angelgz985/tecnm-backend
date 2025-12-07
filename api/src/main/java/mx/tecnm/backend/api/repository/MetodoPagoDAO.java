package mx.tecnm.backend.api.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import mx.tecnm.backend.api.models.MetodoPago;

@Repository
public class MetodoPagoDAO {

    @Autowired
    private JdbcClient jdbcClient;

    public List<MetodoPago> obtenerMetodosPago() {
        String sql = "SELECT id, nombre, comision FROM metodos_pago";
        return jdbcClient.sql(sql)
                .query(new MetodoPagoRM())
                .list();
    }

    public MetodoPago obtenerMetodoPagoPorId(int id) {
        String sql = "SELECT id, nombre, comision FROM metodos_pago WHERE id = ?";
        return jdbcClient.sql(sql)
                .param(id)
                .query(new MetodoPagoRM())
                .single();
    }

    public MetodoPago crearMetodoPago(MetodoPago metodoPago) {
        String sql = "INSERT INTO metodos_pago (nombre, comision) VALUES (?, ?) RETURNING id, nombre, comision";
        return jdbcClient.sql(sql)
                .params(
                    metodoPago.nombre(),
                    metodoPago.comision()
                )
                .query(new MetodoPagoRM())
                .single();
    }

    public MetodoPago actualizarMetodoPago(int id, MetodoPago metodoPago) {
        String sql = "UPDATE metodos_pago SET nombre = ?, comision = ? WHERE id = ? RETURNING id, nombre, comision";
        return jdbcClient.sql(sql)
                .params(
                    metodoPago.nombre(),
                    metodoPago.comision(),
                    id
                )
                .query(new MetodoPagoRM())
                .single();
    }

    public void eliminarMetodoPago(int id) {
        String sql = "DELETE FROM metodos_pago WHERE id = ?";
        jdbcClient.sql(sql)
                .param(id)
                .update();
    }
}