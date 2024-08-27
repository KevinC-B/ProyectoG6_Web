package proyecto.perfumeria.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import proyecto.perfumeria.services.CorreoService;
import org.springframework.core.io.ByteArrayResource;

@Service
public class CorreoServiceImpl implements CorreoService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void enviarCorreoHtml(
            String para,
            String asunto,
            String contenidoHtml)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(para);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true);
        mailSender.send(message);
    }

    @Override
    public void enviarCorreoConAdjunto(
            String para,
            String asunto,
            String contenidoHtml,
            byte[] archivoAdjunto,
            String nombreArchivo) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(para);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true);

        // Adjuntar el archivo PDF
        helper.addAttachment(nombreArchivo, new ByteArrayResource(archivoAdjunto));

        mailSender.send(message);
    }
}
