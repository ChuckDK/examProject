package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpSMTPSettingsAdmin extends Pane {


    private Label titleLabel;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private TextField hostTextField;
    private TextField portTextField;
    private TextField emailAddressTextField;
    private Button applyChangesButton;
    private Button cancelButton;

    public PopUpSMTPSettingsAdmin()
    {
        //initializing elements
        emailAddressTextField = new TextField();

        titleLabel = new Label("SMTP Settings");

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

        emailAddressTextField.setLayoutX(30);
        emailAddressTextField.setLayoutY(80);
        emailAddressTextField.setPrefWidth(200);

        usernameTextField.setLayoutX(30);
        usernameTextField.setLayoutY(140);
        usernameTextField.setPrefWidth(150);

        passwordTextField.setLayoutX(250);
        passwordTextField.setLayoutY(140);
        passwordTextField.setPrefWidth(150);

        hostTextField.setLayoutX(30);
        hostTextField.setLayoutY(200);
        hostTextField.setPrefWidth(100);

        portTextField.setLayoutX(250);
        portTextField.setLayoutY(200);
        portTextField.setPrefWidth(100);

        applyChangesButton.setLayoutX(30);
        applyChangesButton.setLayoutY(350);

        cancelButton.setLayoutX(250);
        cancelButton.setLayoutY(350);

        //prompt texts
        emailAddressTextField.setPromptText("\"From\" email address");
        usernameTextField.setPromptText("Username");
        passwordTextField.setPromptText("Password");
        hostTextField.setPromptText("Host");
        portTextField.setPromptText("Port");

        //Add all elements to view
        this.getChildren().addAll(
                cancelButton,
                emailAddressTextField,
                usernameTextField,
                passwordTextField,
                hostTextField,
                portTextField,
                applyChangesButton,
                titleLabel);
    }
}
