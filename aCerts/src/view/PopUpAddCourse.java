package view;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpAddCourse extends Pane {
    private DatePicker startDatePicker;
    private DatePicker endDatePicker;
    private Label courseNameLabel;
    private TextField courseNameTextField;
    private Label startDateLabel;
    private Label endDateLabel;
    private Button uploadCourseMaterialButton;
    private Button addCourseButton;
    private Button cancelButton;

    public PopUpAddCourse()
    {
        //initialize elements
        //DatePickers
        startDatePicker = new DatePicker();

        endDatePicker =  new DatePicker();

        //Labels
        courseNameLabel = new Label("Course name: ");

        startDateLabel = new Label("Start date");

        endDateLabel = new Label("End date");

        //Textfield
        courseNameTextField = new TextField();

        //Buttons
        uploadCourseMaterialButton = new Button("Upload Course Material");

        addCourseButton = new Button("Add course");

        cancelButton = new Button("Cancel");


        //set prompt text for the textfield
        courseNameTextField.setPromptText("Please input name");


        //layouting
        courseNameLabel.setLayoutX(30);
        courseNameLabel.setLayoutY(85);

        courseNameTextField.setLayoutX(140);
        courseNameTextField.setLayoutY(80);
        courseNameTextField.setPrefWidth(200);

        startDateLabel.setLayoutX(30);
        startDateLabel.setLayoutY(145);

        startDatePicker.setLayoutX(140);
        startDatePicker.setLayoutY(140);
        startDatePicker.setPrefWidth(110);

        endDateLabel.setLayoutX(30);
        endDateLabel.setLayoutY(205);

        endDatePicker.setLayoutX(140);
        endDatePicker.setLayoutY(200);
        endDatePicker.setPrefWidth(110);

        uploadCourseMaterialButton.setLayoutX(270);
        uploadCourseMaterialButton.setLayoutY(200);

        addCourseButton.setLayoutX(30);
        addCourseButton.setLayoutY(350);

        cancelButton.setLayoutX(300);
        cancelButton.setLayoutY(350);

        this.getChildren().addAll(
                courseNameLabel,
                courseNameTextField,
                startDateLabel,
                startDatePicker,
                endDatePicker,
                endDateLabel,
                uploadCourseMaterialButton,
                addCourseButton,
                cancelButton);
    }
}
