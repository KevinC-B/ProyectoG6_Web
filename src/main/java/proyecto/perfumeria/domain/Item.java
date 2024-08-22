package proyecto.perfumeria.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto {
    private int cantidad; //Almacenar la cantidad de items de un producto

    public Item() {
    }
    public Item(Producto producto) {
        super.setIdProducto(producto.getIdProducto());
        super.setNombreProducto(producto.getNombreProducto());
        super.setMarca(producto.getMarca());
        super.setDescripcion(producto.getDescripcion());
        super.setPrecio(producto.getPrecio());
        super.setTamano(producto.getTamano());
        super.setActivo(producto.isActivo());
        super.setExistencias(producto.getExistencias());
        super.setRutaImagen(producto.getRutaImagen());
        this.cantidad = 0;
    }
}
