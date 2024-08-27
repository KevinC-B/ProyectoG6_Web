package proyecto.perfumeria.services;

import jakarta.mail.MessagingException;

public interface CorreoService {

    public void enviarCorreoHtml(
            String para,
            String asunto,
            String contenidoHtml)
            throws MessagingException;
    
    // Nuevo método para enviar un correo con un PDF adjunto
    public void enviarCorreoConAdjunto(
            String para,
            String asunto,
            String contenidoHtml,
            byte[] archivoAdjunto,
            String nombreArchivo)
            throws MessagingException;
}
