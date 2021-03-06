package view.settings;

import control.settings.SMTPSettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.settings.SMTPSettingsFile;
import view.styling.ACertsColorScheme;

public class PopUpSMTPSettingsAdmin extends Pane
{
    private Label titleLabel;
    private TextField usernameTextField;
    private PasswordField passwordTextField;
    private TextField hostTextField;
    private TextField portTextField;
    private TextField emailAddressTextField;
    private Button applyChangesButton;
    private Button cancelButton;

    public PopUpSMTPSettingsAdmin()
    {
        //Create the label, the five text fields, and the two buttons.
        emailAddressTextField = new TextField();

        titleLabel = new Label("SMTP Settings");

        usernameTextField = new TextField();

        passwordTextField = new PasswordField();

        hostTextField = new TextField();

        portTextField = new TextField();

        applyChangesButton = new Button("Apply changes");

        cancelButton = new Button("Cancel");

        ////Set the position and size of the nodes in the pane.
        titleLabel.setLayoutX(130);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(Font.font(40));

        emailAddressTextField.setLayoutX(60);
        emailAddressTextField.setLayoutY(80);
        emailAddressTextField.setPrefWidth(200);

        usernameTextField.setLayoutX(60);
        usernameTextField.setLayoutY(140);
        usernameTextField.setPrefWidth(150);

        passwordTextField.setLayoutX(280);
        passwordTextField.setLayoutY(140);
        passwordTextField.setPrefWidth(150);

        hostTextField.setLayoutX(60);
        hostTextField.setLayoutY(200);
        hostTextField.setPrefWidth(100);

        portTextField.setLayoutX(280);
        portTextField.setLayoutY(200);
        portTextField.setPrefWidth(100);

        applyChangesButton.setLayoutX(60);
        applyChangesButton.setLayoutY(270);
        applyChangesButton.setPrefSize(100, 50);

        cancelButton.setLayoutX(280);
        cancelButton.setLayoutY(270);
        cancelButton.setPrefSize(100, 50);

        //Set prompt text for the text fields.
        emailAddressTextField.setPromptText("\"From\" email address");
        usernameTextField.setPromptText("Username");
        passwordTextField.setPromptText("Password");
        hostTextField.setPromptText("Host");
        portTextField.setPromptText("Port");

        //Styling the nodes with ACertsColorScheme.
        emailAddressTextField.setStyle(ACertsColorScheme.textFieldColor());
        usernameTextField.setStyle(ACertsColorScheme.textFieldColor());
        passwordTextField.setStyle(ACertsColorScheme.textFieldColor());
        hostTextField.setStyle(ACertsColorScheme.textFieldColor());
        portTextField.setStyle(ACertsColorScheme.textFieldColor());
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        applyChangesButton.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());

        //Add all elements to view.
        this.getChildren().addAll(
                cancelButton,
                emailAddressTextField,
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
            String host = (hostTextField.getText().equals("")) ? SMTPSettings.getHost() : hostTextField.getText();
            int port = (portTextField.getText().equals("")) ? SMTPSettings.getPort() : Integer.parseInt(portTextField.getText());
            String password = (passwordTextField.getText().equals("")) ? SMTPSettings.getPassword() : passwordTextField.getText();
            String username = (usernameTextField.getText().equals("")) ? SMTPSettings.getUsername() : usernameTextField.getText();
            String email = (emailAddressTextField.getText().equals("")) ? SMTPSettings.getEmail() : emailAddressTextField.getText();

            //Add the retrieved data to the static class, MySQLSettings,
            //thereby setting it's variables for this run of the program.
            SMTPSettings.setHost(host);
            SMTPSettings.setPort(port);
            SMTPSettings.setUsername(username);
            SMTPSettings.setPassword(password);
            SMTPSettings.setEmail(email);

            //Attempt to save the data set in the section above so the program will have them
            //when the it runs again.
            try
            {
                SMTPSettings.writeObject("SMTPSettingsFile", new SMTPSettingsFile(
                        username,
                        password,
                        host,
                        port,
                        email));
            } catch (Exception e1)
            {
                e1.printStackTrace();
            }
        });
    }
}
