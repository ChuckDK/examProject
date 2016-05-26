package control.operations;

import control.certificates.CourseCertificateTemplateGenerator;
import control.settings.FileIO;
import control.settings.SMTPSettings;
import javafx.scene.image.Image;
import model.certificates.CertificateTemplate;
import model.coursedata.CourseParticipant;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import java.io.File;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;


/**
 * Created by dennis on 5/13/16.
 */
public class SMTPOperations {
    public static boolean sendMissingCertificate(String templateName, CourseParticipant participant)
    {
        CertificateTemplate template = null;
        File templateDataFile = new File("templates/"+templateName);
        if(!templateDataFile.exists())
        {
            FTPOperations.downloadFile(templateName, "templates");
            templateDataFile = new File("templates/"+templateName);
        }

        try
        {
            template = (CertificateTemplate) FileIO.readObject("templates/"+templateDataFile.getName());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        File imageFile = new File("templates/"+template.getFilename());
        if(!imageFile.exists())
        {
            FTPOperations.downloadFile(template.getFilename(), "templates");
            imageFile = new File("templates/"+template.getFilename());
        }
        try
        {
            CourseCertificateTemplateGenerator.generateCertificateImage(template, imageFile, participant);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  true;
    }

    public static void sendCertificate(String to, String msg, String subject, File attachement)
    {

        /*PRIMARY SETTINGS*/
        // Sender's email ID needs to be mentioned
        String from = SMTPSettings.getEmail();
        final String username = SMTPSettings.getUsername();//change accordingly
        final String password = SMTPSettings.getPassword();//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = SMTPSettings.getHost();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", SMTPSettings.getPort());

        /*PRIMARY SETTINGS END*/

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(msg);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = attachement.getAbsolutePath();
            System.out.println(filename);
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            System.out.println(message);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
