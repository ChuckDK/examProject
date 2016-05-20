package view.start;

import control.settings.FTPSettings;
import control.settings.MySQLSettings;
import control.settings.SMTPSettings;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.settings.FTPSettingsFile;
import model.settings.MySQLSettingsFile;
import model.settings.SMTPSettingsFile;
import view.styling.Resizable;
import view.login.ViewPaneLogin;

//Class which runs an instance of other classes
//as all the windows of the program essentially are created by instances of their respective classes.
public class SceneInitializer {

    private static Stage mainWindow;

    //Static method which creates all the views and then initializes a window with the login screen.
    public static void initialize(Stage window)
    {
        //A try-catch where settings files are read and their data placed in the different settings in the program.
        //See the controller package for more information regarding the methods' functionality.
        try
        {
            MySQLSettingsFile sqlSettings = (MySQLSettingsFile) MySQLSettings.readObject("MySQLSettingsFile");
            FTPSettingsFile ftpSettings = (FTPSettingsFile) FTPSettings.readObject("FTPSettingsFile");
            SMTPSettingsFile smtpSettings = (SMTPSettingsFile) SMTPSettings.readObject("SMTPSettingsFile");

            MySQLSettings.setDatabaseName(sqlSettings.getDatabaseName());
            MySQLSettings.setPort(sqlSettings.getPort());
            MySQLSettings.setHost(sqlSettings.getHost());
            MySQLSettings.setPassword(sqlSettings.getPassword());
            MySQLSettings.setUsername(sqlSettings.getUserName());

            FTPSettings.setHost(ftpSettings.getHost());
            FTPSettings.setPassword(ftpSettings.getPassword());
            FTPSettings.setPort(ftpSettings.getPort());
            FTPSettings.setUsername(ftpSettings.getUsernamer());

            SMTPSettings.setEmail(smtpSettings.getEmail());
            SMTPSettings.setPort(smtpSettings.getPort());
            SMTPSettings.setUsername(smtpSettings.getUserName());
            SMTPSettings.setHost(smtpSettings.getHost());
            SMTPSettings.setPassword(smtpSettings.getPassword());
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
        mainWindow = window;
        Scene scene = new Scene(new ViewPaneLogin(), 500, 400);
        window.show();
        window.setScene(scene);
        window.setTitle("AppAcademy Certificate Manager");

        window.getScene().widthProperty().addListener(e-> updateView(scene));
        window.getScene().heightProperty().addListener(e-> updateView(scene));
    }

    public static void updateView(Scene scene)
    {
        //check if the current view implements Resizeable, if it does then calls its UpdateLayout method
        if(scene.getRoot() instanceof Resizable)
        {
            ((Resizable) scene.getRoot()).updateLayout();
        }
    }

    public static Stage getMainWindow()
    {
        return mainWindow;
    }
}
