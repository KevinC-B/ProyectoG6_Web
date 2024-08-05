package proyecto.perfumeria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.services.FirebaseStorageService;
import proyecto.perfumeria.services.ProductoService;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = productoService.getProductos(false); //Se obtienen los productos activos
        model.addAttribute("productos", lista); //Añade la lista de productos para poder verlos en la web
        model.addAttribute("totalProductos", lista.size()); //Añade el total de productos

        return "/producto/listado"; //Hace retorno de donde se visualiza la lista de productos
    }
    
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
    @PostMapping("/guardar")
    public String guardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            String rutaImagen = firebaseStorageService
                    .cargaImagen(imagenFile, "producto", producto.getIdProducto()); //Esto carga la img en el storage y devuelve la ruta publica que tiene la img
            producto.setRutaImagen(rutaImagen);
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }
    
    @GetMapping("/eliminar/{idProducto}") //Mapeamos hacía "eliminar" junto con el id del producto que se quiere eliminar
    public String eliminar(Producto producto) { //Agarra el "{idProducto}" con sus atributos y lo mete en el objeto del párametro "producto"
        productoService.delete(producto); //Instrucción para borrar el registro
        return "redirect:/producto/listado"; //Retorna a esa dirección
    }
    
    @GetMapping("/modificar/{idProducto}") //Mapeamos hacía "modificar" junto con el id del producto que se quiere modificar
    public String modificar(Producto producto, Model model) { //<-Párametros 
        producto = productoService.getProducto(producto); //En el objeto "producto" recupera todos los atributos del producto seleccionada
        model.addAttribute("producto", producto); //Se inyectan los atributos a este model para que se usen en la dirección del return
        return "/producto/modifica"; //Retorna a esa dirección
    }
}
