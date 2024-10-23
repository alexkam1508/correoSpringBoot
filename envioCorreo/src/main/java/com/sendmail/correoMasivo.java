package com.sendmail;
import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;

//import ch.qos.logback.core.read.ListAppender;

import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class correoMasivo {
        // Inyecta la dependencia de JavaMailSender, que es responsable de enviar correos electrónicos
    @Autowired
    private JavaMailSender mailSender;
 
    // Mapea la solicitud HTTP POST a la ruta "enviarCorreoMasivo"
    @PostMapping("enviarCorreoMasivo")
    public ResponseEntity<?> enviarCorreoMasivo() {
        // Definir una lista de direcciones de correo electrónico a las cuales se enviarán los correos
        List<String> correos = Arrays.asList(
            "kevinandrade259@gmail.com", // Primera dirección de correo
            "hernandezxa412@gmail.com", // Segunda dirección de correo
            "correo3@example.com"  // Tercera dirección de correo
        );
 
        try {
            // Iterar sobre cada dirección de correo en la lista
            for (String correo : correos) {
                // Crear un objeto SimpleMailMessage para configurar los detalles del correo
                SimpleMailMessage email = new SimpleMailMessage();
                email.setTo(correo); // Establecer el destinatario del correo
                email.setFrom("alex.kamarillo@gmail.com"); // Establecer el remitente
                email.setSubject("Correo masivo de prueba"); // Establecer el asunto del correo
                email.setText("Este es un correo de prueba enviado de forma masiva puto."); // Establecer el contenido del correo
 
                // Enviar el correo electrónico utilizando el objeto JavaMailSender
                mailSender.send(email);
            }
 
            // Retornar una respuesta HTTP 200 OK si los correos se enviaron con éxito
            return new ResponseEntity<>("Correos enviados exitosamente", HttpStatus.OK);
 
        } catch (Exception e) {
            // Si ocurre algún error, imprimir el seguimiento de la pila y devolver una respuesta HTTP 500
            e.printStackTrace();
            return new ResponseEntity<>("Error al enviar los correos: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

}
