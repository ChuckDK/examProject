package view.settings;

import control.settings.MySQLSettings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import model.settings.MySQLSettingsFile;
import view.styling.ACertsColorScheme;

public class PopUpMySQLSettingsAdmin extends Pane{
    private Label titleLabel;
    private TextField hostTextField;
    private TextField databaseNameTextField;
    private TextField portTextField;
    private TextField usernameTextField;
    private PasswordField passwordTextField;
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

        passwordTextField = new PasswordField();

        applyChangesButton = new Button("Apply changes");

        cancelButton = new Button("Cancel");


        //Set the position and size of the nodes in the pane.
        titleLabel.setLayoutX(100);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(Font.font(40));

        usernameTextField.setLayoutX(60);
        usernameTextField.setLayoutY(200);
        usernameTextField.setPrefWidth(100);

        passwordTextField.setLayoutX(280);
        passwordTextField.setLayoutY(200);
        passwordTextField.setPrefWidth(100);

        hostTextField.setLayoutX(60);
        hostTextField.setLayoutY(80);
        hostTextField.setPrefWidth(130);

        portTextField.setLayoutX(280);
        portTextField.setLayoutY(140);
        portTextField.setPrefWidth(100);

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

        //Styling the nodes with ACertsColorScheme.
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

        //Adding a function for the applyChangesButton button.
        applyChangesButton.setOnAction(e->
        {
            //The syntax functions as an if-statement: (Compares) ? (if it is true) : (if it is false).
            //The variable is equal to the outcome of this statement. Furthermore a wrapping is needed if the variable .
            String host = (hostTextField.getText().equals("")) ? MySQLSettings.getHost() : hostTextField.getText();
            int port = (portTextField.getText().equals("")) ? MySQLSettings.getPort() : Integer.parseInt(portTextField.getText());
            String password = (passwordTextField.getText().equals("")) ? MySQLSettings.getPassword() : passwordTextField.getText();
            String username = (usernameTextField.getText().equals("")) ? MySQLSettings.getUsername() : usernameTextField.getText();
            String databaseName = (databaseNameTextField.getText().equals("")) ? MySQLSettings.getDatabaseName() : databaseNameTextField.getText();

            //Add the retrieved data to the static class, MySQLSettings,
            //thereby setting it's variables for this run of the program.
            MySQLSettings.setHost(host);
            MySQLSettings.setPassword(password);
            MySQLSettings.setUsername(username);
            MySQLSettings.setPort(port);
            MySQLSettings.setDatabaseName(databaseName);

            //Attempt to save the data set in the section above so the program will have them
            //when the it runs again.
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