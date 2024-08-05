package proyecto.perfumeria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data //Getters y Setters
@Entity //Esta clase mapea una tabla de la BD
@Table(name = "producto") //Referencia a la tabla de la BD
public class Producto implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id //Para identificar el atributo primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Para dejar que la BD se preocupe del auto increment
    @Column(name = "id_Producto") //Para asociar atributo con el espacio en la BD
    private Long idProducto;

    private String nombreProducto;
    private String marca;
    private String descripcion;
    private float precio;
    private String tamano;
    private boolean activo;
    private String rutaImagen;

}
