package view.courses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import view.styling.ACertsColorScheme;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpAddCoursePersona extends Pane{
    private Label firstNameLabel;
    private Label lastNameLabel;
    private TextField firstNameTextField;
    private TextField lastNameTextField;
    private Label emailLabel;
    private TextField emailTextField;
    private Label phoneNumberLabel;
    private TextField phoneNumberTextField;
    private Label phoneNumber2Label;
    private TextField phoneNumber2TextField;
    private Button cancelButton;

    public PopUpAddCoursePersona()
    {
        //initialize elements
        firstNameLabel = new Label("First name:");

        lastNameLabel = new Label("Last name:");

        firstNameTextField = new TextField();

        lastNameTextField = new TextField();

        emailLabel = new Label("E-mail:");

        emailTextField = new TextField();

        phoneNumberLabel = new Label("Phone #");

        phoneNumberTextField = new TextField();

        phoneNumber2Label = new Label("Phone #2");

        phoneNumber2TextField = new TextField();

        cancelButton = new Button("Cancel");


        //prompt texts
        firstNameTextField.setPromptText("Please input name");

        lastNameTextField.setPromptText("Please input name");

        emailTextField.setPromptText("Input email");

        phoneNumberTextField.setPromptText("please input #");

        phoneNumber2TextField.setPromptText("please input #");


        //layouting

        firstNameLabel.setLayoutX(30);
        firstNameLabel.setLayoutY(85);

        firstNameTextField.setLayoutX(120);
        firstNameTextField.setLayoutY(80);
        firstNameTextField.setPrefWidth(120);

        lastNameLabel.setLayoutX(30);
        lastNameLabel.setLayoutY(145);

        lastNameTextField.setLayoutX(120);
        lastNameTextField.setLayoutY(140);
        lastNameTextField.setPrefWidth(120);

        emailLabel.setLayoutX(250);
        emailLabel.setLayoutY(85);

        emailTextField.setLayoutX(320);
        emailTextField.setLayoutY(80);
        emailTextField.setPrefWidth(160);

        phoneNumberLabel.setLayoutX(30);
        phoneNumberLabel.setLayoutY(245);

        phoneNumberTextField.setLayoutX(100);
        phoneNumberTextField.setLayoutY(240);
        phoneNumberTextField.setPrefWidth(120);

        phoneNumber2Label.setLayoutX(250);
        phoneNumber2Label.setLayoutY(245);

        phoneNumber2TextField.setLayoutX(320);
        phoneNumber2TextField.setLayoutY(240);
        phoneNumber2TextField.setPrefWidth(120);

        cancelButton.setLayoutX(300);
        cancelButton.setLayoutY(350);

        //Add elements to the view
        this.getChildren().addAll(
                cancelButton,
                firstNameLabel,
                lastNameLabel,
                firstNameTextField,
                lastNameTextField,
                emailLabel,
                emailTextField,
                phoneNumberLabel,
                phoneNumberTextField,
                phoneNumber2Label,
                phoneNumber2TextField);

        //Styling
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        firstNameTextField.setStyle(ACertsColorScheme.textFieldColor());
        lastNameTextField.setStyle(ACertsColorScheme.textFieldColor());
        emailTextField.setStyle(ACertsColorScheme.textFieldColor());
        phoneNumberTextField.setStyle(ACertsColorScheme.textFieldColor());
        phoneNumber2TextField.setStyle(ACertsColorScheme.textFieldColor());
        this.setStyle(ACertsColorScheme.viewColor());
    }
}
