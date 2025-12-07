package mx.tecnm.backend.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.tecnm.backend.api.models.MetodoPago;
import mx.tecnm.backend.api.repository.MetodoPagoDAO;

@RestController
@RequestMapping("/metodos-pago")
public class MetodoPagoController {

    @Autowired
    MetodoPagoDAO repo;

    @GetMapping()
    public ResponseEntity<List<MetodoPago>> obtenerMetodosPago() {
        List<MetodoPago> resultado = repo.obtenerMetodosPago();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetodoPago> obtenerMetodoPagoPorId(@PathVariable int id) {
        try {
            MetodoPago metodoPago = repo.obtenerMetodoPagoPorId(id);
            return ResponseEntity.ok(metodoPago);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping()
    public ResponseEntity<MetodoPago> crearMetodoPago(@RequestBody MetodoPago metodoPago) {
        MetodoPago metodoPagoCreado = repo.crearMetodoPago(metodoPago);
        return ResponseEntity.ok(metodoPagoCreado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPago> actualizarMetodoPago(@PathVariable int id, @RequestBody MetodoPago metodoPago) {
        try {
            MetodoPago metodoPagoActualizado = repo.actualizarMetodoPago(id, metodoPago);
            return ResponseEntity.ok(metodoPagoActualizado);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable int id) {
        try {
            repo.eliminarMetodoPago(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}