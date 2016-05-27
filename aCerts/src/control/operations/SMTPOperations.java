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

public class SMTPOperations {

    //Method which sends a certificate to a course participant who is missing a certificate.
    public static boolean sendMissingCertificate(String templateName, CourseParticipant participant)
    {
        //Initialize a template object.
        CertificateTemplate template = null;

        //Initialize a file which is equal to a file located in the templates folder with a given name.
        File templateDataFile = new File("templates/"+templateName);

        //If the file doesn't exist, download it from the FTP server.
        if(!templateDataFile.exists())
        {
            FTPOperations.downloadFile(templateName, "templates");
            templateDataFile = new File("templates/"+templateName);
        }

        //Try making a template object by reading it with an input stream from the newly
        //created templateDataFile in the templates folder.
        try
        {
            template = (CertificateTemplate) FileIO.readObject("templates/"+templateDataFile.getName());
        }
        //Handle the exception
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //Initialize a file which is equal to the streamed files object from before.
        File imageFile = new File("templates/"+template.getFilename());

        //If the file doesn't exist, download it from the FTP server.
        if(!imageFile.exists())
        {
            FTPOperations.downloadFile(template.getFilename(), "templates");
            imageFile = new File("templates/"+template.getFilename());
        }

        //Try to create a certificate image using the newly created template object, the streamed template object file,
        //and the participant object provided by the scope.
        try
        {
            CourseCertificateTemplateGenerator.generateCertificateImage(template, imageFile, participant);
        }
        //Handle any exceptions.
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  true;
    }

    //Method which sends a certificate.
    //this method is an edited version of teh example provided at:
    //http://www.codejava.net/java-se/networking/ftp/java-ftp-file-download-tutorial-and-example         -27/05-2016
    public static void sendCertificate(String to, String msg, String subject, File attachement)
    {
        //Initialize a string variable to hold the email value from SMTPSettings.
        String from = SMTPSettings.getEmail();

        //Initialize some final string variables to hold the username and password values from SMTPSettings.
        final String username = SMTPSettings.getUsername();//change accordingly
        final String password = SMTPSettings.getPassword();//change accordingly

        //Initialize a string variable to hold the host value from SMTPSettings.
        String host = SMTPSettings.getHost();

        //Initialize a properties object to hold some listeners.
        //This makes it so the data in the program is updated according to the certificates which has been sent.
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", SMTPSettings.getPort());

        //Create a session object which is equal to the current instance of the program,
        //which holds the property values, a new javax.mail.Authenticator() method instance which holds a method
        //called getPasswordAuthentication which returns a new PasswordAuthentication object
        //which holds the final username and password values.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            //Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            //The following is code manipulating the text fields in the mail:

            //Set text in the "From" text field.
            message.setFrom(new InternetAddress(from));

            //Set text in the "To" text field.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            //Set text in the "Subject" text field.
            message.setSubject(subject);

            //Set text in the message part.
            BodyPart messageBodyPart = new MimeBodyPart();

            //Set the actual message in the message part which is given in the scope.
            messageBodyPart.setText(msg);

            //Create a multipart message
            Multipart multipart = new MimeMultipart();

            //Set a text part in the multi text message.
            multipart.addBodyPart(messageBodyPart);

            //Create a clone of the messageBodyPart BodyPart variable
            messageBodyPart = new MimeBodyPart();

            //Get the full file path from the file provided in the scope.
            String filename = attachement.getAbsolutePath();

            //Initialize a new datasource object using our filepath. Datasource makes a connection between the file
            //and the program so the file can be read.
            DataSource source = new FileDataSource(filename);

            //Set a data handler which handles the data source object according to the messageBodyPart.
            //Before this step, the datasource object cannot be utilized.
            messageBodyPart.setDataHandler(new DataHandler(source));

            //sets the "filename" parameter of the header of messageBodyPart to point to the file path.
            messageBodyPart.setFileName(filename);

            //Add the mime body part to the MimeMultipart multipart.
            multipart.addBodyPart(messageBodyPart);

            //Message is the "container" of our content, and it contains the multipart object.
            message.setContent(multipart);

            //Send message "container".
            Transport.send(message);
        }

        //Handle any messaging exceptions.
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
