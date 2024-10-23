package com.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//definimos clase como un controlador REST

@RestController
public class enviarCorreo {
    @Autowired
    private JavaMailSender mail;

    @PostMapping("enviarCorreo")

    public ResponseEntity<?> enviar_correo(
        @RequestParam String to, //Destinatario
        @RequestParam String subject, //Asunto
        @RequestParam String message //Mensaje
        ) {
        // crear objeto para almacenar el mensaje
        try {
            // Crea un nuevo objeto para configurar el correo
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo("kevinandrade259@gmail.com"); // Asigna destinatario
            email.setFrom("alex.kamaurillo@gmail.com"); // Remitente
            email.setSubject("Prueba de correo puto"); // Asigna asunto
            email.setText(" prueba de correo para putos putos.com"); // Asigna mensaje 
            
            //Envia correo utilizando el objeto mail
            mail.send(email);

            // Retornar la respuesta http de correo enviado exitosament
            return new ResponseEntity<>("Correo enviado exitosamente", HttpStatus.OK);

        }catch(Exception e){
            //Retorna respuesta http de error
            return new ResponseEntity<>("Error al enviar el correo: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}