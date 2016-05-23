package control.certificates;

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

        String server = FTPSettings.getHost();
        int port = FTPSettings.getPort();
        String user = FTPSettings.getUsername();
        String pass = FTPSettings.getPassword();

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // APPROACH #1: uploads first file using an InputStream
            File image = file;

            String remoteImage = file.getName();
            InputStream inputStream = new FileInputStream(image);

            System.out.println("Start uploading file");
            boolean done = ftpClient.storeFile(remoteImage, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }

            // APPROACH #1: uploads first file using an InputStream
            File template = file;

            String remoteTemplate = file.getName();
            inputStream = new FileInputStream(template);

            System.out.println("Start uploading file");
            done = ftpClient.storeFile(remoteTemplate, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }
}
