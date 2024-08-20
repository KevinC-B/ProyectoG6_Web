package proyecto.perfumeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "proyecto.perfumeria") // Asegúrate de que el paquete base esté incluido
public class PerfumeriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerfumeriaApplication.class, args);
    }
}
