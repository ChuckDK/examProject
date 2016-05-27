package view.login;

import control.operations.Login;
import control.operations.MySQLCourseResponsible;
import control.settings.FTPSettings;
import control.settings.MySQLSettings;
import control.settings.SMTPSettings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import model.coursedata.CourseResponsible;
import model.settings.FTPSettingsFile;
import model.settings.MySQLSettingsFile;
import model.settings.SMTPSettingsFile;
import view.managers.ViewPanesManager;
import view.start.SceneInitializer;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

public class ViewPaneLogin extends Pane implements Resizable
{
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
        //Initialize the logo in an image view.
        //The reason for the subtraction is that 200 refers to half of the window's
        //startup width and height. Therefore 200 is just a pin-pointer to make it easier to find the correct position.
        //Here the AppAcademy.png is used as an example.
        appAcademyLogo = new ImageView(new Image("AppAcademy.png"));
        appAcademyLogo.setLayoutX(200 - 50);
        appAcademyLogo.setLayoutY(200 - 180);

        //Message label to show message if login failed for instance.
        messageLabel = new Label("");

        //Initialize label to prompt user to insert username in the Text box next to it and set its position.
        usernameLabel = new Label("Please insert Username:");
        usernameLabel.setLayoutX(200 - 180);
        usernameLabel.setLayoutY(200 - 30);

        //Initialize text box for user to input username and set it's position.
        usernameTextField = new TextField();
        usernameTextField.setLayoutX(200);
        usernameTextField.setLayoutY(200 - 30);
        usernameTextField.setPrefWidth(180);

        //Initialize label to prompt user to insert password in the Text box next to it and set it's start position.
        passwordLabel = new Label("Please input Password:");
        passwordLabel.setLayoutX(200 - 180);
        passwordLabel.setLayoutY(200 + 10);

        //Initialize text box for user to input password and set it's position.
        passwordTextField = new PasswordField();
        passwordTextField.setLayoutX(200);
        passwordTextField.setLayoutY(200 + 10);
        passwordTextField.setPrefWidth(180);

        //Initialize button to login and set it's position.
        loginButton = new Button("Login");
        loginButton.setLayoutX(200 - 40);
        loginButton.setLayoutY(200 + 50);
        loginButton.setPrefWidth(80);

        //Initialize button to exit the program and set it's position.
        cancelButton = new Button("Exit");
        cancelButton.setLayoutX(400 - 80);
        cancelButton.setLayoutY(400 - 40);
        cancelButton.setPrefWidth(60);

        //Add all elements to the instance of the class and thereby make them visible.
        this.getChildren().addAll(
                usernameLabel,
                passwordLabel,
                usernameTextField,
                messageLabel,
                passwordTextField,
                loginButton,
                cancelButton,
                appAcademyLogo);

        //Set styling of the elements using ACertsColorScheme.
        this.setStyle(ACertsColorScheme.viewColor());

        usernameTextField.setStyle(ACertsColorScheme.textFieldColor());
        passwordTextField.setStyle(ACertsColorScheme.textFieldColor());

        loginButton.setStyle(ACertsColorScheme.buttonColor());
        cancelButton.setStyle(ACertsColorScheme.buttonColor());


        //Add functionality for the loginButton button.
        loginButton.setOnAction(e->
        {
            //An if-statement which evaluates whether or not the user has admin rights
            //and also evaluates if the login information is correct.
            //See 'ViewPanesManager' class for the entire functionality
            ViewPanesManager viewPanesManager;
            CourseResponsible user = MySQLCourseResponsible.login(usernameTextField.getText(), passwordTextField.getText());

            if(user == null)
            {
                messageLabel.setText("LoginFailed");
            }
            else
            {
                Login.setUserEmail(user.getEmail());
                if (user.hasAdminRights())
                {
                    viewPanesManager = ViewPanesManager.getInstance(true);
                } else
                {
                    viewPanesManager = ViewPanesManager.getInstance(false);
                }
                //Initialize the starting scene where you get the first window
                //from the array in the ViewPanesManager class.
                //The getPane(0) will differ depending on whether or not the user has admin rights.
                Scene scene = new Scene(viewPanesManager.getPane(0), 1280, 720);
                SceneInitializer.getMainWindow().setScene(scene);
                scene.widthProperty().addListener(ex1-> SceneInitializer.updateView(scene));
                scene.heightProperty().addListener(ex1-> SceneInitializer.updateView(scene));
                ((Resizable) viewPanesManager.getPane(0)).updateLayout();
            }
        });
    }


    /**this methods repositions all the visible elements relative to the center or the lower-right corner of the window
    this method is called if the window is resized*/
    @Override
    public void updateLayout()
    {
        //Here we store the center coordinates in order to use them later.
        double centerX = this.getScene().getWidth() / 2;
        double centerY = this.getScene().getHeight() / 2;

        //Using the stored coordinates, the position of the nodes are set.
        appAcademyLogo.setLayoutX(centerX - 50);
        appAcademyLogo.setLayoutY(centerY - 180);

        messageLabel.setLayoutX(centerX - messageLabel.getWidth()/2);
        messageLabel.setLayoutY(centerY - 70);

        usernameLabel.setLayoutX(centerX - 180);
        usernameLabel.setLayoutY(centerY - 30);

        usernameTextField.setLayoutX(centerX);
        usernameTextField.setLayoutY(centerY - 30);

        passwordLabel.setLayoutX(centerX - 180);
        passwordLabel.setLayoutY(centerY + 10);

        passwordTextField.setLayoutX(centerX);
        passwordTextField.setLayoutY(centerY + 10);

        loginButton.setLayoutX(centerX - 40);
        loginButton.setLayoutY(centerY + 50);

        cancelButton.setLayoutX(centerX * 2 - 80);
        cancelButton.setLayoutY(centerY * 2 - 40);
    }
}