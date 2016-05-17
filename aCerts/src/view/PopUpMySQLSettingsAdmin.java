package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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

        usernameTextField.setLayoutX(100);
        usernameTextField.setLayoutY(160);
        usernameTextField.setPrefWidth(100);

        passwordTextField.setLayoutX(280);
        passwordTextField.setLayoutY(160);
        passwordTextField.setPrefWidth(100);

        hostTextField.setLayoutX(100);
        hostTextField.setLayoutY(80);
        hostTextField.setPrefWidth(130);

        portTextField.setLayoutX(280);
        portTextField.setLayoutY(120);
        portTextField.setPrefWidth(100);

        databaseNameTextField.setLayoutX(100);
        databaseNameTextField.setLayoutY(120);
        databaseNameTextField.setPrefWidth(100);

        applyChangesButton.setLayoutX(100);
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
    }
}
