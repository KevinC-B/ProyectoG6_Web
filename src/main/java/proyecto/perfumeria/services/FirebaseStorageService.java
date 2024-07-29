
package proyecto.perfumeria.services;

import org.springframework.web.multipart.MultipartFile;

public interface FirebaseStorageService {

    public String cargaImagen(MultipartFile archivoLocalCliente, String carpeta, Long id);

    //El BuketName es el <id_del_proyecto> + ".appspot.com"
    final String BucketName = "smell-elegance.appspot.com";

    //Esta es la ruta básica de este proyecto
    final String rutaSuperiorStorage = "SmellElegance";

    //Ubicación donde se encuentra el archivo de configuración Json
    final String rutaJsonFile = "firebase";
    
    //El nombre del archivo Json
    final String archivoJsonFile = "smell-elegance-firebase-adminsdk-1tet7-2c66b25a41.json";
}