package control.operations;

import control.settings.FTPSettings;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FTPOperations {
    public static boolean uploadFile(File file)
    {
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

            String remoteImage = file.getName();
            InputStream inputStream = new FileInputStream(file);

            System.out.println("Start uploading file");
            boolean done = ftpClient.storeFile(remoteImage, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeFTPClient(ftpClient);
        }
        return true;
    }

    public static boolean downloadFile(String filename, String subfolder)
    {
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

            // APPROACH #1: using retrieveFile(String, OutputStream)
            File downloadFile1 = new File(subfolder+"/"+filename);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(filename, outputStream1);
            outputStream1.close();

            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            closeFTPClient(ftpClient);
        }
        return true;
    }

    private static void closeFTPClient(FTPClient ftpClient)
    {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
