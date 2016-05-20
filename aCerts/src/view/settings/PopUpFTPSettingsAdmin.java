package view.settings;

import control.settings.FTPSettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.settings.FTPSettingsFile;
import view.styling.ACertsColorScheme;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpFTPSettingsAdmin extends Pane
{
    private Label titleLabel;

    private TextField usernameTextField;
    private PasswordField passwordTextField;
    private TextField hostTextField;
    private TextField portTextField;

    private Button applyChangesButton;
    private Button cancelButton;

    public PopUpFTPSettingsAdmin()
    {
        //Create the label, the four text fields, and the two buttons.
        titleLabel = new Label("FTP Settings");

        usernameTextField = new TextField();

        passwordTextField = new PasswordField();

        hostTextField = new TextField();

        portTextField = new TextField();

        applyChangesButton = new Button("Apply changes");

        cancelButton = new Button("Cancel");


        //Set the position and size of the nodes in the pane.
        titleLabel.setLayoutX(130);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(Font.font(40));

        usernameTextField.setLayoutX(60);
        usernameTextField.setLayoutY(80);
        usernameTextField.setPrefWidth(150);

        passwordTextField.setLayoutX(280);
        passwordTextField.setLayoutY(80);
        passwordTextField.setPrefWidth(150);

        hostTextField.setLayoutX(60);
        hostTextField.setLayoutY(140);
        hostTextField.setPrefWidth(100);

        portTextField.setLayoutX(280);
        portTextField.setLayoutY(140);
        portTextField.setPrefWidth(100);

        applyChangesButton.setLayoutX(60);
        applyChangesButton.setLayoutY(270);
        applyChangesButton.setPrefSize(100, 50);

        cancelButton.setLayoutX(280);
        cancelButton.setLayoutY(270);
        cancelButton.setPrefSize(100, 50);

        //Set prompt text for the text fields.
        usernameTextField.setPromptText("Username");
        passwordTextField.setPromptText("Password");
        hostTextField.setPromptText("Host");
        portTextField.setPromptText("Port");

        //Styling the nodes with ACertsColorScheme.
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        applyChangesButton.setStyle(ACertsColorScheme.buttonColor());
        usernameTextField.setStyle(ACertsColorScheme.textFieldColor());
        passwordTextField.setStyle(ACertsColorScheme.textFieldColor());
        hostTextField.setStyle(ACertsColorScheme.textFieldColor());
        portTextField.setStyle(ACertsColorScheme.textFieldColor());
        this.setStyle(ACertsColorScheme.viewColor());

        //Add all elements to view.
        this.getChildren().addAll(
                cancelButton,
                usernameTextField,
                passwordTextField,
                hostTextField,
                portTextField,
                applyChangesButton,
                titleLabel);

        //Adding a function for the applyChangesButton button.
        applyChangesButton.setOnAction(e->
            {
                //The syntax functions as an if-statement: (Compares) ? (if it is true) : (if it is false).
                //The variable is equal to the outcome of this statement. Furthermore a wrapping is needed if the variable .
                String host = (hostTextField.getText().equals("")) ? FTPSettings.getHost() : hostTextField.getText();
                int port = (portTextField.getText().equals("")) ? FTPSettings.getPort() : Integer.parseInt(portTextField.getText());
                String username = (usernameTextField.getText().equals("")) ? FTPSettings.getUsername() : usernameTextField.getText();
                String password = (passwordTextField.getText().equals("")) ? FTPSettings.getPassword() : passwordTextField.getText();

                //Add the retrieved data to the static class, MySQLSettings,
                //thereby setting it's variables for this run of the program.
                FTPSettings.setHost(host);
                FTPSettings.setPort(port);
                FTPSettings.setUsername(username);
                FTPSettings.setPassword(password);

                //Attempt to save the data set in the section above so the program will have them
                //when the it runs again.
                try
                {
                    FTPSettings.writeObject("FTPSettingsFile", new FTPSettingsFile(username, password, host, port));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        );
    }
}