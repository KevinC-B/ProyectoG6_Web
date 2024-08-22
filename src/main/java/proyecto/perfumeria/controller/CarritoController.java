package proyecto.perfumeria.controller;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import jakarta.servlet.http.HttpServletResponse;
import proyecto.perfumeria.domain.Item;
import proyecto.perfumeria.domain.Producto;
import proyecto.perfumeria.services.ItemService;
import proyecto.perfumeria.services.ProductoService;
import proyecto.perfumeria.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    //Para ver el carrito
    @GetMapping("/carrito/listcarrito")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        var carritoTotalVenta = itemService.getTotal();
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return "carrito/listcarrito";
    }

    //Para agregar un producto al carrito
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregarItem(Model model, Item item, @RequestParam("cantidad") int cantidad) {
        Producto producto = productoService.getProducto(item);
        if (producto != null && producto.getExistencias() >= cantidad) {
            Item item2 = itemService.get(item);
            if (item2 == null) {
                item2 = new Item(producto);
                item2.setCantidad(cantidad); // Establecer la cantidad solicitada
            } else {
                item2.setCantidad(item2.getCantidad() + cantidad); // Sumar la cantidad si ya está en el carrito
            }
            itemService.save(item2);
        } else {
            model.addAttribute("error", "Cantidad solicitada supera las existencias disponibles.");
            return new ModelAndView("redirect:/catalogo/listcatalogo");
        }

        var lista = itemService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    //Para modificar un producto del carrito
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificarItem(Item item, Model model) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "carrito/modifica";
    }

    //Para eliminar un elemento del carrito
    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listcarrito";
    }

    //Para actualizar un producto del carrito (cantidad)
    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listcarrito";
    }

    //Para facturar los productos del carrito (no implementado)
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }

    //Vista para el desglose de la factura
    @GetMapping("/carrito/facturacion")
    public String facturacion(Model model) {
        var items = itemService.gets();
        var total = itemService.getTotal();
        var usuario = usuarioService.getUsuarioAutenticado();

        model.addAttribute("items", items);
        model.addAttribute("total", total);
        model.addAttribute("usuario", usuario);

        return "factura/desgloseFactura"; // Vista del desglose de la factura
    }

    //Generación de PDF de la factura
    @GetMapping("/carrito/imprimirFactura")
    public void imprimirFactura(HttpServletResponse response) {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=factura.pdf");

        try {
            var items = itemService.gets();
            var total = itemService.getTotal();
            var usuario = usuarioService.getUsuarioAutenticado();

            // Creación del documento PDF
            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Configuración del documento
            pdf.setDefaultPageSize(PageSize.A4);
            document.setMargins(36, 36, 36, 36); // Márgenes más amplios para un diseño limpio

            // Estilo minimalista
            Style headerStyle = new Style()
                    .setFontSize(18)
                    .setBold()
                    .setFontColor(ColorConstants.BLACK)
                    .setTextAlignment(TextAlignment.CENTER);

            Style subHeaderStyle = new Style()
                    .setFontSize(14)
                    .setBold()
                    .setFontColor(ColorConstants.DARK_GRAY)
                    .setTextAlignment(TextAlignment.LEFT);

            Style bodyStyle = new Style()
                    .setFontSize(12)
                    .setFontColor(ColorConstants.BLACK)
                    .setTextAlignment(TextAlignment.LEFT);

            Style totalStyle = new Style()
                    .setFontSize(14)
                    .setBold()
                    .setFontColor(ColorConstants.BLACK)
                    .setTextAlignment(TextAlignment.RIGHT);

            // Agregar título de la factura
            document.add(new Paragraph("Factura de Compra").addStyle(headerStyle));

            // Espacio
            document.add(new Paragraph("\n"));

            // Información del cliente
            document.add(new Paragraph("Información del Cliente").addStyle(subHeaderStyle));
            document.add(new Paragraph("Nombre: " + usuario.getNombre()).addStyle(bodyStyle));
            document.add(new Paragraph("Correo: " + usuario.getCorreo()).addStyle(bodyStyle));

            // Espacio
            document.add(new Paragraph("\n"));

            // Tabla de productos
            Table tabla = new Table(new float[]{3, 1, 2, 2});
            tabla.setWidth(UnitValue.createPercentValue(100));

            tabla.addHeaderCell(new Cell().add(new Paragraph("Producto").addStyle(subHeaderStyle)));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Cantidad").addStyle(subHeaderStyle)));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Precio Unitario").addStyle(subHeaderStyle)));
            tabla.addHeaderCell(new Cell().add(new Paragraph("Total").addStyle(subHeaderStyle)));

            for (Item item : items) {
                tabla.addCell(new Cell().add(new Paragraph(item.getNombreProducto()).addStyle(bodyStyle)));
                tabla.addCell(new Cell().add(new Paragraph(String.valueOf(item.getCantidad())).addStyle(bodyStyle)));
                tabla.addCell(new Cell().add(new Paragraph("₡" + item.getPrecio()).addStyle(bodyStyle)));
                tabla.addCell(new Cell().add(new Paragraph("₡" + (item.getCantidad() * item.getPrecio())).addStyle(bodyStyle)));
            }

            document.add(tabla);

            // Espacio
            document.add(new Paragraph("\n"));

            // Total a pagar
            document.add(new Paragraph("Total a Pagar: ₡" + total).addStyle(totalStyle));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
