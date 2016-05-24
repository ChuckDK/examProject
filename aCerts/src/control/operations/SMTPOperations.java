package control.operations;

import control.certificates.CourseCertificateTemplateGenerator;
import control.settings.FileIO;
import javafx.scene.image.Image;
import model.certificates.CertificateTemplate;

import java.io.File;

/**
 * Created by dennis on 5/13/16.
 */
public class SMTPOperations {
    public static boolean sendMissingCertificate(String templateName)
    {
        Image image = null;
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
            CourseCertificateTemplateGenerator.generateCertificateImage(template, imageFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return  true;
    }

}
