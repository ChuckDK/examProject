package control.certificates;

import control.settings.FileIO;
import model.certificates.CertificateTemplate;

import java.nio.file.Paths;

public class CourseCertificateTemplateGenerator
{
    public static boolean uploadCertificateTemplate(CertificateTemplate certificateTemplate, String templateName)
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

        return true;
    }
}
