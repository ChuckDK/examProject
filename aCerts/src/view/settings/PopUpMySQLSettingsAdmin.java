package view.settings;

import control.settings.MySQLSettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.settings.MySQLSettingsFile;
import view.styling.ACertsColorScheme;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpMySQLSettingsAdmin extends Pane{
    private Label titleLabel;
    private TextField hostTextField;
    private TextField databaseNameTextField;
    private TextField portTextField;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Button applyChangesButton;
    private Button cancelButton;

    public PopUpMySQLSettingsAdmin()
    {
        //Create the label, the five text fields, and the two buttons.
        titleLabel = new Label("MySQL Settings");

        hostTextField = new TextField();

        databaseNameTextField = new TextField();

        portTextField = new TextField();

        usernameTextField = new TextField();

        passwordTextField = new TextField();

        applyChangesButton = new Button("Apply changes");

        cancelButton = new Button("Cancel");


        //Set the position and size of the nodes in the pane.
        titleLabel.setLayoutX(100);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(Font.font(40));

        //hswnjew
        usernameTextField.setLayoutX(60);
        usernameTextField.setLayoutY(200);
        usernameTextField.setPrefWidth(100);

        passwordTextField.setLayoutX(280);
        passwordTextField.setLayoutY(200);
        passwordTextField.setPrefWidth(100);

        //ewge
        hostTextField.setLayoutX(60);
        hostTextField.setLayoutY(80);
        hostTextField.setPrefWidth(130);

        portTextField.setLayoutX(280);
        portTextField.setLayoutY(140);
        portTextField.setPrefWidth(100);

        //asdf
        databaseNameTextField.setLayoutX(60);
        databaseNameTextField.setLayoutY(140);
        databaseNameTextField.setPrefWidth(100);

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
        databaseNameTextField.setPromptText("Database Name");

        //Styling
        usernameTextField.setStyle(ACertsColorScheme.textFieldColor());
        passwordTextField.setStyle(ACertsColorScheme.textFieldColor());
        hostTextField.setStyle(ACertsColorScheme.textFieldColor());
        portTextField.setStyle(ACertsColorScheme.textFieldColor());
        databaseNameTextField.setStyle(ACertsColorScheme.textFieldColor());
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        applyChangesButton.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());

        //Add all elements to view
        this.getChildren().addAll(
                cancelButton,
                usernameTextField,
                passwordTextField,
                hostTextField,
                portTextField,
                applyChangesButton,
                titleLabel,
                databaseNameTextField);

        //functionality
        applyChangesButton.setOnAction(e->
        {
            String host = (hostTextField.getText().equals("")) ? MySQLSettings.getHost() : hostTextField.getText();
            int port = (portTextField.getText().equals("")) ? MySQLSettings.getPort() : Integer.parseInt(portTextField.getText());
            String password = (passwordTextField.getText().equals("")) ? MySQLSettings.getPassword() : passwordTextField.getText();
            String username = (usernameTextField.getText().equals("")) ? MySQLSettings.getUsername() : usernameTextField.getText();
            String databaseName = (databaseNameTextField.getText().equals("")) ? MySQLSettings.getDatabaseName() : databaseNameTextField.getText();

            MySQLSettings.setHost(host);
            MySQLSettings.setPassword(password);
            MySQLSettings.setUsername(username);
            MySQLSettings.setPort(port);
            MySQLSettings.setDatabaseName(databaseName);

            try
            {
                MySQLSettings.writeObject(
                        "MySQLSettingsFile",
                        new MySQLSettingsFile(username,
                        password,
                        databaseName,
                        host,
                        port));
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
        });
    }
}
