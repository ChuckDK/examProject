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
public class PopUpFTPSettingsAdmin extends Pane
{
    private Label titleLabel;

    private TextField usernameTextField;
    private TextField passwordTextField;
    private TextField hostTextField;
    private TextField portTextField;

    private Button applyChangesButton;
    private Button cancelButton;

    public PopUpFTPSettingsAdmin()
    {
        //initializing elements
        titleLabel = new Label("FTP Settings");

        usernameTextField = new TextField();

        passwordTextField = new TextField();

        hostTextField = new TextField();

        portTextField = new TextField();

        applyChangesButton = new Button("Apply changes");

        cancelButton = new Button("Cancel");


        //layouting
        titleLabel.setLayoutX(100);
        titleLabel.setLayoutY(10);
        titleLabel.setFont(Font.font(40));

        usernameTextField.setLayoutX(30);
        usernameTextField.setLayoutY(80);
        usernameTextField.setPrefWidth(150);

        passwordTextField.setLayoutX(250);
        passwordTextField.setLayoutY(80);
        passwordTextField.setPrefWidth(150);

        hostTextField.setLayoutX(30);
        hostTextField.setLayoutY(140);
        hostTextField.setPrefWidth(100);

        portTextField.setLayoutX(250);
        portTextField.setLayoutY(140);
        portTextField.setPrefWidth(100);

        applyChangesButton.setLayoutX(30);
        applyChangesButton.setLayoutY(350);

        cancelButton.setLayoutX(250);
        cancelButton.setLayoutY(350);

        //prompt texts
        usernameTextField.setPromptText("Username");
        passwordTextField.setPromptText("Password");
        hostTextField.setPromptText("Host");
        portTextField.setPromptText("Port");

        //Add all elements to view
        this.getChildren().addAll(
                cancelButton,
                usernameTextField,
                passwordTextField,
                hostTextField,
                portTextField,
                applyChangesButton,
                titleLabel);
    }
}
