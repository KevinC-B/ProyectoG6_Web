package proyecto.perfumeria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data //Getters y Setters
@Entity //Esta clase mapea una tabla de la BD
@Table(name = "rol") //Referencia a la tabla de la BD
public class Rol implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id //Para identificar el atributo primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Para dejar que la BD se preocupe del auto increment
    @Column(name = "id_rol") //Para asociar atributo con el espacio en la BD
    private Long idRol;

    private String nombre;
    @Column(name = "id_usuario") //Para asociar atributo con el espacio en la BD
    private Long idUsuario;
}
