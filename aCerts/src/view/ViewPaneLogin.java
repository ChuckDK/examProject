package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneLogin extends Pane implements Resizable{
    private Label messageLabel;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField usernameTextField;
    private PasswordField passwordTextField;
    private Button loginButton;
    private Button cancelButton;
    private ImageView appAcademyLogo;

    public ViewPaneLogin()
    {
        //initialize the logo in an imageview and set its startposition 200 refers to half of the window's
        //startup width and height
        appAcademyLogo = new ImageView(new Image("AppAcademy.png"));
        appAcademyLogo.setLayoutX(200 - 50);
        appAcademyLogo.setLayoutY(200 - 180);

        //messagelabel to show message if login failed for instance
        messageLabel = new Label("");

        //initialize label to prompt user to insert username in the Textbox next to it and set its startposition
        usernameLabel = new Label("Please insert Username:");
        usernameLabel.setLayoutX(200 - 180);
        usernameLabel.setLayoutY(200 - 30);

        //initialize textbox for user to input username and set its startposition
        usernameTextField = new TextField();
        usernameTextField.setLayoutX(200);
        usernameTextField.setLayoutY(200 - 30);
        usernameTextField.setPrefWidth(180);

        //initialize label to prompt user to insert password in the Textbox next to it and set its startposition
        passwordLabel = new Label("Please input Password:");
        passwordLabel.setLayoutX(200 - 180);
        passwordLabel.setLayoutY(200 + 10);

        //initialize textbox for user to input password and set its startposition
        passwordTextField = new PasswordField();
        passwordTextField.setLayoutX(200);
        passwordTextField.setLayoutY(200 + 10);
        passwordTextField.setPrefWidth(180);

        //initialize button to login and set its startposition
        loginButton = new Button("Login");
        loginButton.setLayoutX(200 - 40);
        loginButton.setLayoutY(200 + 50);
        loginButton.setPrefWidth(80);

        //initialize button to exit the program and set its startposition
        cancelButton = new Button("Exit");
        cancelButton.setLayoutX(400 - 80);
        cancelButton.setLayoutY(400 - 40);
        cancelButton.setPrefWidth(60);

        //add all elements to the pane so that they will be visible
        this.getChildren().addAll(
                usernameLabel,
                passwordLabel,
                usernameTextField,
                messageLabel,
                passwordTextField,
                loginButton,
                cancelButton,
                appAcademyLogo);

        //set styling of the elements
        this.setStyle(ACertsColorScheme.viewColor());

        usernameTextField.setStyle(ACertsColorScheme.textFieldColor());
        passwordTextField.setStyle(ACertsColorScheme.textFieldColor());

        loginButton.setStyle(ACertsColorScheme.buttonBcolor());
        cancelButton.setStyle(ACertsColorScheme.buttonBcolor());


        //Functionality
        loginButton.setOnAction(e->
        {
            ViewPanesManager viewPanesManager = ViewPanesManager.getInstance(false);
            Scene scene = new Scene(ViewPanesManager.getInstance(true).getPane(0));
            SceneInitializer.getMainWindow().setScene(scene);
            scene.widthProperty().addListener(ex1-> SceneInitializer.updateView(scene));
            scene.heightProperty().addListener(ex1-> SceneInitializer.updateView(scene));
            ((Resizable) viewPanesManager.getPane(0)).updateLayout();
        });
    }


    /**this methods repositions all the visible elements relative to the center or the lower-right corner of the window
    this method is called if the window is resized*/
    @Override
    public void updateLayout()
    {
        //here we store the center coordinates to use for later
        double centerX = this.getScene().getWidth() / 2;
        double centerY = this.getScene().getHeight() / 2;

        //this moves the logo relative to the center of the window and moves it 180 pixels up. the - 50 is half of the width
        // of the image which will make it centered on the x-axis
        appAcademyLogo.setLayoutX(centerX - 50);
        appAcademyLogo.setLayoutY(centerY - 180);

        ///this moves the messageLabel relative to the center of the window
        messageLabel.setLayoutX(centerX - messageLabel.getWidth()/2);
        messageLabel.setLayoutY(centerY - 70);

        //this moves the usernameLabel relative to the center of the window
        usernameLabel.setLayoutX(centerX - 180);
        usernameLabel.setLayoutY(centerY - 30);

        //this moves the usernameTextField relative to the center of the window
        usernameTextField.setLayoutX(centerX);
        usernameTextField.setLayoutY(centerY - 30);

        //this moves the passwordLabel relative to the center of the window
        passwordLabel.setLayoutX(centerX - 180);
        passwordLabel.setLayoutY(centerY + 10);

        //this moves the passwordTextField relative to the center of the window
        passwordTextField.setLayoutX(centerX);
        passwordTextField.setLayoutY(centerY + 10);

        //this moves the loginButton relative to the center of the window
        loginButton.setLayoutX(centerX - 40);
        loginButton.setLayoutY(centerY + 50);

        //this moves the cancel button to the lower-right corner of the window
        cancelButton.setLayoutX(centerX * 2 - 80);
        cancelButton.setLayoutY(centerY * 2 - 40);
    }
}
