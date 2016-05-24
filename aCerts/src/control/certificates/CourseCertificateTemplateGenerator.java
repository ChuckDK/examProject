package control.certificates;

import control.operations.FTPOperations;
import control.settings.FTPSettings;
import control.settings.FileIO;
import model.certificates.CertificateTemplate;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;

import java.io.*;
import java.nio.file.Paths;

public class CourseCertificateTemplateGenerator
{
    public static boolean uploadCertificateTemplate(CertificateTemplate certificateTemplate, String templateName, File file)
    {
        try
        {
            FileIO.writeObject(Paths.get("").toAbsolutePath().toString()+"/templates/"+templateName, certificateTemplate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        FTPOperations.uploadFile(file);
        try
        {
            FTPOperations.uploadFile(new File(Paths.get("").toAbsolutePath().toString()+"/templates/"+templateName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean generateCertificateImage(CertificateTemplate template, File imageFile)
    {
        return false;
    }
}
