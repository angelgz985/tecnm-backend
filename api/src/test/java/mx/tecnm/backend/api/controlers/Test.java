package mx.tecnm.backend.api.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.modells.Producto;

@RequestMapping("/test")
@RestController
public class Test {

    @GetMapping("/hello")
    public String helloWorld(){
        return "Hola API Rest";
    }

    @GetMapping("/producto")
    public Producto getProducto(){
        Producto producto = new Producto();
        producto.nombre = "Yogurt Danone Fresa";
        producto.codigoBarras = "7588443100043";
        producto.precio = 12.0;

        return producto;
    }

    @GetMapping("/productos")
    public Producto[] getProductos(){
        Producto producto1 = new Producto();
        producto1.nombre = "Yogurt Danone Fresa";
        producto1.codigoBarras = "7588443100043";
        producto1.precio = 12.0;

        Producto producto2 = new Producto();
        producto2.nombre = "Leche Lala 1lt";
        producto2.codigoBarras = "7588443190343";
        producto2.precio = 22.50;

        Producto producto3 = new Producto();
        producto3.nombre = "Queso manchego 200g";
        producto3.codigoBarras = "7505643190343";
        producto3.precio = 45.75;

        Producto productos[] = {producto1, producto2, producto3};

        return productos;
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable int id){
        if(id < 0 || id > 2){
            return ResponseEntity.notFound().build();
        }
        
        Producto producto1 = new Producto();
        producto1.nombre = "Yogurt Danone Fresa";
        producto1.codigoBarras = "7588443100043";
        producto1.precio = 12.0;

        Producto producto2 = new Producto();
        producto2.nombre = "Leche Lala 1lt";
        producto2.codigoBarras = "7588443190343";
        producto2.precio = 22.50;

        Producto producto3 = new Producto();
        producto3.nombre = "Queso manchego 200g";
        producto3.codigoBarras = "7505643190343";
        producto3.precio = 45.75;

        Producto[] productos = {producto1, producto2, producto3};
        return ResponseEntity.ok(productos[id]);
    }
}