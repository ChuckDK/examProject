package view.courses;

import control.operations.Login;
import control.operations.SQLOperations;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coursedata.CourseResponsible;
import view.courseresponsibles.PopUpAddCourseResponsible;
import view.courseresponsibles.PopUpCourseResponsibleSelection;
import view.courseresponsibles.PopUpCourseResponsibleSelectionList;
import view.styling.ACertsColorScheme;

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
    protected Button assignCourseResponsible;

    private CourseResponsible courseResponsible;

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

        assignCourseResponsible = new Button("Assign Course Responsible");


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

        assignCourseResponsible.setLayoutX(270);
        assignCourseResponsible.setLayoutY(140);

        this.getChildren().addAll(
                cancelButton,
                courseNameLabel,
                courseNameTextField,
                startDateLabel,
                startDatePicker,
                endDatePicker,
                endDateLabel,
                uploadCourseMaterialButton,
                addCourseButton);

        //styling
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        courseNameTextField.setStyle(ACertsColorScheme.textFieldColor());
        startDatePicker.setStyle(ACertsColorScheme.textFieldColor());
        endDatePicker.setStyle(ACertsColorScheme.textFieldColor());
        uploadCourseMaterialButton.setStyle(ACertsColorScheme.buttonColor());
        addCourseButton.setStyle(ACertsColorScheme.buttonColor());
        assignCourseResponsible.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());

        //functionality
        assignCourseResponsible.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Add new course responsible");
            PopUpCourseResponsibleSelection popupPane = new PopUpCourseResponsibleSelection();
            popup.setScene(new Scene(popupPane, 500, 400));

            popup.initModality(Modality.APPLICATION_MODAL);

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex -> {
                Stage stage = new Stage();
                Pane pane = new PopUpCourseResponsibleSelectionList();
                Scene scene = new Scene(pane);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                //This line enables functionality for the 'cancelButton' in the 'PopUpCourseResponsibleSelectionList' class
                //hence the number  which refers to the 0-indexed number where the 'cancelButton' is added.
                ((Button) pane.getChildren().get(0)).setOnAction(ev -> stage.close());
                popup.close();
                stage.showAndWait();

            });
            ((Button) popupPane.getChildren().get(1)).setOnAction(ex -> {
                Stage stage = new Stage();
                Pane pane = new PopUpAddCourseResponsible();
                Scene scene = new Scene(pane, 500, 400);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                //This line enables functionality for the 'cancelButton' in the 'PopUpAddCoursePersona' class
                //hence the number 0 which refers to the 0-indexed number where the 'cancelButton' is added.
                ((Button) pane.getChildren().get(0)).setOnAction(a -> stage.close());
                popup.close();
                stage.showAndWait();
            });
            popup.show();
        });
    }

    public boolean checkForValues()
    {
        String courseResponsibleEmail = "";
        boolean allInfoFilledIn = true;
        DropShadow error = new DropShadow();
        error.setColor(Color.RED);
        String startDate = "";
        String endDate = "";
        try
        {
            startDate = "" + startDatePicker.getValue().getYear() + "-" + startDatePicker.getValue().getMonthValue() + "-" + startDatePicker.getValue().getDayOfMonth();
            startDatePicker.setEffect(null);
        }
        catch (NullPointerException n)
        {
            startDatePicker.setEffect(error);
            allInfoFilledIn = false;
        }
        try
        {
            endDate = "" + endDatePicker.getValue().getYear() + "-" + endDatePicker.getValue().getMonthValue() + "-" + endDatePicker.getValue().getDayOfMonth();
            endDatePicker.setEffect(null);
        }
        catch (NullPointerException n)
        {
            endDatePicker.setEffect(error);
            allInfoFilledIn = false;
        }

        String courseName = courseNameTextField.getText();

        if(courseName.equals(""))
        {
            courseNameTextField.setEffect(error);
            allInfoFilledIn = false;
        }
        else
        {
            courseNameTextField.setEffect(null);
        }
        if(courseResponsible == null)
        {
            courseResponsibleEmail = Login.getUserEmail();
        }
        if(allInfoFilledIn)
        {
            String[] values = {"course_id", "'"+courseName+"'", "'"+startDate+"'", "'"+endDate+"'", "'"+courseResponsibleEmail+"'", "1"};
            if(SQLOperations.addNewRow("courses", values))
            {
                return true;
            }
            else
            {
                System.out.println("error");
                return false;
            }
        }
        return false;
    }
}
