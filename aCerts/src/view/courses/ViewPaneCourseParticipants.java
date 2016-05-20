package view.courses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coursedata.CourseParticipant;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

import java.util.ArrayList;

public class ViewPaneCourseParticipants extends Pane implements Resizable
{

    private TableView<CourseParticipant> courseParticipantTableView;
    private ToggleButton missingCertificatesToggleButton;
    private ToggleButton allCourseParticipantsToggleButton;
    private ToggleButton filteredCourseParticipantsToggleButton;
    private Button removeParticipantButton;
    private Button addNewParticipantButton;

    public ViewPaneCourseParticipants()
    {
        //create courses objects from mysql database and add them to temporary arraylist
        //for now just a dummy object

        CourseParticipant dummy = new CourseParticipant("machine code", "John", "Wick", "jw@dummy.com", "112",false,  new Button());
        ArrayList<CourseParticipant> coursesArray= new ArrayList<>();
        coursesArray.add(dummy);

        //create an observablelist from our arraylist and create tableview

        ObservableList<CourseParticipant> courses = FXCollections.observableArrayList(coursesArray);
        courseParticipantTableView = new TableView<>();
        courseParticipantTableView.itemsProperty().setValue(courses);

        //create columns for our tableview

        TableColumn<CourseParticipant, String> courseNameColumn = new TableColumn<>("Course");
        TableColumn<CourseParticipant, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<CourseParticipant, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<CourseParticipant, String> emailColumn = new TableColumn<>("Email");
        TableColumn<CourseParticipant, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        TableColumn<CourseParticipant, String> certificateSentColumn = new TableColumn<>("certificate Sent");
        TableColumn<CourseParticipant, Button> sendColumn = new TableColumn<>("Send Certificate");

        //link columns to values in our Course object

        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        certificateSentColumn.setCellValueFactory(new PropertyValueFactory<>("certificateSent"));
        sendColumn.setCellValueFactory(new PropertyValueFactory<>("sendButton"));


        //add the columns to our courseTableView
        courseParticipantTableView.getColumns().addAll(
                courseNameColumn,
                firstNameColumn,
                lastNameColumn,
                emailColumn,
                phoneNumberColumn,
                sendColumn);

        //group togglebuttons
        ToggleGroup group = new ToggleGroup();

        missingCertificatesToggleButton = new ToggleButton("Missing");
        allCourseParticipantsToggleButton = new ToggleButton("All");
        filteredCourseParticipantsToggleButton = new ToggleButton("Filtered");

        missingCertificatesToggleButton.setToggleGroup(group);
        allCourseParticipantsToggleButton.setToggleGroup(group);
        filteredCourseParticipantsToggleButton.setToggleGroup(group);

        addNewParticipantButton = new Button("Add new");

        removeParticipantButton = new Button("remove");

        //add elements to view
        this.getChildren().addAll(
                missingCertificatesToggleButton,
                filteredCourseParticipantsToggleButton,
                allCourseParticipantsToggleButton,
                addNewParticipantButton,
                removeParticipantButton,
                courseParticipantTableView);


        //layouting togglebuttons
        courseParticipantTableView.setLayoutX(100);
        courseParticipantTableView.setLayoutY(10);

        missingCertificatesToggleButton.setLayoutX(10);
        missingCertificatesToggleButton.setLayoutY(20);

        allCourseParticipantsToggleButton.setLayoutX(10);
        allCourseParticipantsToggleButton.setLayoutY(50);

        filteredCourseParticipantsToggleButton.setLayoutX(10);
        filteredCourseParticipantsToggleButton.setLayoutY(80);

        //set the "active" togglebutton to be selected at first
        missingCertificatesToggleButton.setSelected(true);

        //layouting buttons these buttons y position is dependant on the window size so it will be set in the updateLayout
        //method
        addNewParticipantButton.setLayoutX(10);
        removeParticipantButton.setLayoutX(10);

        //set the width of the sidemenu elements. They are set here and not in the updateLayout method, since their width
        //is not dependant on the size of the window
        missingCertificatesToggleButton.setPrefWidth(100);
        allCourseParticipantsToggleButton.setPrefWidth(100);
        filteredCourseParticipantsToggleButton.setPrefWidth(100);

        addNewParticipantButton.setPrefWidth(80);
        removeParticipantButton.setPrefWidth(80);

        //styling
        this.setStyle(ACertsColorScheme.viewColor());

        missingCertificatesToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());
        allCourseParticipantsToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());
        filteredCourseParticipantsToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());

        addNewParticipantButton.setStyle(ACertsColorScheme.buttonColor());
        removeParticipantButton.setStyle(ACertsColorScheme.buttonColor());

        //functionality
        addNewParticipantButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Add new Course Participant");
            PopUpAddCourseParticipant popupPane = new PopUpAddCourseParticipant();
            popup.setScene(new Scene(popupPane,  500, 400));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });
    }

    @Override
    public void updateLayout()
    {
        //sets the editorviews size to be inside the current window with a little spacing added. 310 is the size of
        //the editor sidemenu (300) plus a margin of 10.
        courseParticipantTableView.setPrefWidth(this.getScene().getWidth() - 110);

        //60 represents a margin of 10 on top and bottom and an estimated height of 40 of the tabpane
        courseParticipantTableView.setPrefHeight(this.getScene().getHeight() - 60);

        //set the addNewCourses button to be above the bottom of the window. 60 represents a margin of 10 from the removebutton and
        // bottom and an estimated height of 40 of the tabpane
        addNewParticipantButton.setLayoutY(this.getScene().getHeight() - addNewParticipantButton.getHeight() - removeParticipantButton.getHeight() - 60);

        //set the addNewCourses button to be above the bottom of the window. 50 represents a margin of 10 from the
        // bottom and an estimated height of 40 of the tabpane
        removeParticipantButton.setLayoutY(this.getScene().getHeight() - removeParticipantButton.getHeight() - 50);

    }
}
