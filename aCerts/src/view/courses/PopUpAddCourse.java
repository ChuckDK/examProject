package view.courses;

import control.operations.Login;
import control.operations.MySQLCourses;
import control.operations.SQLOperations;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.certificates.TemplateID;
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
    private ComboBox<TemplateID> templates;
    protected Button assignCourseResponsible;

    private CourseResponsible courseResponsible;

    public PopUpAddCourse()
    {
        //Initialize elements:

        //Date pickers.
        startDatePicker = new DatePicker();
        endDatePicker =  new DatePicker();

        //Labels.
        courseNameLabel = new Label("Course name: ");
        startDateLabel = new Label("Start date");
        endDateLabel = new Label("End date");

        //Textfield.
        courseNameTextField = new TextField();

        //Buttons.
        uploadCourseMaterialButton = new Button("Upload Course Material");
        addCourseButton = new Button("Add course");
        cancelButton = new Button("Cancel");
        assignCourseResponsible = new Button("Assign Course Responsible");

        //Combo box.
        templates = new ComboBox<>();

        //Add data to combo box.
        templates.itemsProperty().setValue(FXCollections.observableArrayList(MySQLCourses.getTemplateList()));

        //Set prompt text for the textfield.
        courseNameTextField.setPromptText("Please input name");

        //Set the position of the date pickers, labels, buttons, text field and combo box and using setLayoutX and setLayoutY.
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

        templates.setLayoutX(270);
        templates.setLayoutY(260);

        //Add all elements to the instance of the class.
        this.getChildren().addAll(
                cancelButton,
                courseNameLabel,
                courseNameTextField,
                startDateLabel,
                startDatePicker,
                endDatePicker,
                endDateLabel,
                uploadCourseMaterialButton,
                addCourseButton,
                templates);

        //Styling the elements by using ACertsColorScheme.
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        courseNameTextField.setStyle(ACertsColorScheme.textFieldColor());
        startDatePicker.setStyle(ACertsColorScheme.textFieldColor());
        endDatePicker.setStyle(ACertsColorScheme.textFieldColor());
        uploadCourseMaterialButton.setStyle(ACertsColorScheme.buttonColor());
        addCourseButton.setStyle(ACertsColorScheme.buttonColor());
        assignCourseResponsible.setStyle(ACertsColorScheme.buttonColor());
        templates.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());

        //Add functionality to the assignCourseResponsible button.
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

                //This line enables functionality for the 'cancelButton' in the
                //'PopUpCourseResponsibleSelectionList' class
                //hence the number  which refers to the 0-indexed number where the 'cancelButton' is added.
                ((Button) pane.getChildren().get(0)).setOnAction(ev -> stage.close());

                //This line enables functionality for the 'selectCourseResponsibleButton' button in the
                //'PopUpCourseResponsibleSelectionList' class,
                ((Button) pane.getChildren().get(3)).setOnAction(ev ->
                {
                    //The courseResponsible variable is assigned to the selected value from the table view.
                    courseResponsible = ((CourseResponsible) ((TableView) pane.getChildren().get(1)).getSelectionModel().getSelectedItem());
                    stage.close();
                });
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

    //Method which ensures all the values has been added before they are added to the database.
    public boolean checkForValues()
    {
        String courseResponsibleEmail = "";
        boolean allInfoFilledIn = true;

        //A DropShadow is an effect which can be applied to an element in JavaFX.
        //It will color the area around the element.
        DropShadow error = new DropShadow();
        error.setColor(Color.RED);
        String startDate = "";
        String endDate = "";

        //If the start date is null, throw an exception and activate the drop shadow effect.
        try
        {
            startDate = "" + startDatePicker.getValue().getYear() + "-" + startDatePicker.getValue().getMonthValue() +
                    "-" + startDatePicker.getValue().getDayOfMonth();
            startDatePicker.setEffect(null);
        }
        catch (NullPointerException n)
        {
            startDatePicker.setEffect(error);
            allInfoFilledIn = false;
        }

        //If the end date is null, throw an exception, set the allInforFilledIn to false,
        //and activate the drop shadow effect.
        try
        {
            endDate = "" + endDatePicker.getValue().getYear() + "-" + endDatePicker.getValue().getMonthValue() + "-" +
                    "" + endDatePicker.getValue().getDayOfMonth();
            endDatePicker.setEffect(null);
        }
        catch (NullPointerException n)
        {
            endDatePicker.setEffect(error);
            allInfoFilledIn = false;
        }

        //Saving the result of the courseNameTextField in a variable for convenience.
        String courseName = courseNameTextField.getText();

        //If the course name text field is null, throw an exception, set the allInfoFilledIn to false,
        //and activate the drop shadow effect.
        if(courseName.equals(""))
        {
            courseNameTextField.setEffect(error);
            allInfoFilledIn = false;
        }
        else
        {
            courseNameTextField.setEffect(null);
        }

        //If the course responsible object is null, throw an exception, set the allInforFilledIn to false,
        //and activate the drop shadow effect.
        if(courseResponsible == null)
        {
            courseResponsibleEmail = Login.getUserEmail();
        }
        else
        {
            courseResponsibleEmail = courseResponsible.getEmail();
        }

        //If allInfoFilledIn is true, which essentially means every other field was filled in correctly,
        //then take the filled in info and add to the database. If this somehow fail and
        //SQLOperations.addNewRow("courses", values) evaluates to false, ""error" is printed.
        if(allInfoFilledIn)
        {
            String[] values = {"course_id", "'"+courseName+"'", "'"+startDate+"'", "'"+endDate+"'",
                    "'"+courseResponsibleEmail+"'", Integer.toString(templates.getValue().getTemplateID())};
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
