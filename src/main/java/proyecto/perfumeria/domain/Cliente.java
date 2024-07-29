package proyecto.perfumeria.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data; //se generan los get y set por defecto

@Data
@Entity //esta clase va a mapear una tabla de la base de datos
@Table(name="cliente")
public class Cliente implements Serializable{ //Serializable para poder enviar objetos a internet
    
    private static final long serialVersionUID =1l;//se va a usar internamente para asignar los valores
    
    @Id //por medio del atributo de abajo se mapean las filas
    @GeneratedValue(strategy = GenerationType.IDENTITY)//que la base de datos sea la que se encargue de asignar el id 
    @Column(name="cedula") //Da a notar que el valor de abajo va a representar cierta columna de la tabla
    private long cedula;
    
    private String nombre;
    private String ape_Paterno;
    private String ape_Materno;
    private Date fecha_Nacimiento;
    private String correo;
    private String telefono;
    private boolean activo;
    
}
