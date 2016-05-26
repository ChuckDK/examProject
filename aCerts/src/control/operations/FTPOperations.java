package control.operations;

import control.settings.FTPSettings;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;

public class FTPOperations {

    //Method which uploads a file to the FTP Server.
    public static boolean uploadFile(File file)
    {
        //Initializing variables an assigning them data from the FTSettings class.
        String server = FTPSettings.getHost();
        int port = FTPSettings.getPort();
        String user = FTPSettings.getUsername();
        String pass = FTPSettings.getPassword();

        //Initializing an FTPClient from the org.apache.commons.net.ftp.FTPClient library.
        FTPClient ftpClient = new FTPClient();

        try {
            //Connect, log in, and set the client to be in passive mode.
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            //Indicate which file type will be used.
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //Initialize the file name as a string.
            String remoteImage = file.getName();

            //Initialize an input stream which has the file in its scope.
            InputStream inputStream = new FileInputStream(file);

            //Print "Start uploading file"
            System.out.println("Start uploading file");

            //Try to upload the image by a certain filename.
            boolean done = ftpClient.storeFile(remoteImage, inputStream);

            //Close the stream.
            inputStream.close();

            //Check whether the file was uploaded successfully.
            if (done) {
                System.out.println("The file is uploaded successfully.");
            }

            //If the file isn't uploaded successfully,
            //the catch handles the exception and writes "Error: " + the error message.
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        //Finally, use method which close the FTP client.
        finally {
            closeFTPClient(ftpClient);
        }
        //We haven't implemented the feature to return true or false depending on how the upload went.
        return true;
    }

    //Method which downloads a file to the FTP Server.
    public static boolean downloadFile(String filename, String subfolder)
    {
        //Initializing variables an assigning them data from the FTSettings class.
        String server = FTPSettings.getHost();
        int port = FTPSettings.getPort();
        String user = FTPSettings.getUsername();
        String pass = FTPSettings.getPassword();

        //Indicate which file type will be used.
        FTPClient ftpClient = new FTPClient();

        try {
            //Connect, log in, set the client to be in passive mode, and indicate which file type will be used.
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            //Initialize the file, put the file in an output stream, try to download the image by a certain filename,
            //and then close the output stream.
            File downloadFile1 = new File(subfolder+"/"+filename);
            OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1));
            boolean success = ftpClient.retrieveFile(filename, outputStream1);
            outputStream1.close();

            //Check whether the file was downloaded successfully.
            if (success) {
                System.out.println("File #1 has been downloaded successfully.");
            }

            //If the file isn't downloaded successfully,
            //the catch handles the exception and writes "Error: " + the error message.
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        //Finally, use method which close the FTP client.
        finally {
            closeFTPClient(ftpClient);
        }

        //We haven't implemented the feature to return true or false depending on how the download went.
        return true;
    }

    //Method which checks whether the ftp client is connected, and if it is log out and then disconnect.
    //If something fails, print the stacktrace of the exception.
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
