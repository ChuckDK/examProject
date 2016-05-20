package view.courseresponsibles;

import control.operations.MySQLCourseResponsible;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coursedata.CourseResponsible;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

import java.util.ArrayList;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneCourseResponsiblesAdmin extends Pane implements Resizable
{

    private TableView<CourseResponsible> courseResponsiblesTableView;
    private ToggleButton inActiveCourseResponsiblesToggleButton;
    private ToggleButton activeCourseResponsiblesToggleButton;
    private Button removeCourseResponsibleButton;
    private Button addNewCourseResponsibleButton;

    public ViewPaneCourseResponsiblesAdmin()
    {
        //create courses objects from mysql database and add them to temporary arraylist
        //for now just a dummy object

        ArrayList<CourseResponsible> coursesArray = MySQLCourseResponsible.getAll();

        //create an observablelist from our arraylist and create tableview

        ObservableList<CourseResponsible> courses = FXCollections.observableArrayList(coursesArray);
        courseResponsiblesTableView = new TableView<>();
        courseResponsiblesTableView.itemsProperty().setValue(courses);

        //create columns for our tableview

        TableColumn<CourseResponsible, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<CourseResponsible, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<CourseResponsible, String> emailColumn = new TableColumn<>("Email");
        TableColumn<CourseResponsible, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        TableColumn<CourseResponsible, String> phoneNumber2Column = new TableColumn<>("Phone Number 2");

        //link columns to values in our Course object

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumber2Column.setCellValueFactory(new PropertyValueFactory<>("phoneNumber2"));


        //add the columns to our courseTableView
        courseResponsiblesTableView.getColumns().addAll(
                firstNameColumn,
                lastNameColumn,
                emailColumn,
                phoneNumberColumn,
                phoneNumber2Column);

        //group togglebuttons
        ToggleGroup group = new ToggleGroup();

        inActiveCourseResponsiblesToggleButton = new ToggleButton("Active");
        activeCourseResponsiblesToggleButton = new ToggleButton("All");

        inActiveCourseResponsiblesToggleButton.setToggleGroup(group);
        activeCourseResponsiblesToggleButton.setToggleGroup(group);

        addNewCourseResponsibleButton = new Button("Add new");

        removeCourseResponsibleButton = new Button("remove");

        //add elements to view
        this.getChildren().addAll(
                inActiveCourseResponsiblesToggleButton,
                activeCourseResponsiblesToggleButton,
                addNewCourseResponsibleButton,
                removeCourseResponsibleButton,
                courseResponsiblesTableView);


        //layouting togglebuttons
        courseResponsiblesTableView.setLayoutX(100);
        courseResponsiblesTableView.setLayoutY(10);

        inActiveCourseResponsiblesToggleButton.setLayoutX(10);
        inActiveCourseResponsiblesToggleButton.setLayoutY(20);

        activeCourseResponsiblesToggleButton.setLayoutX(10);
        activeCourseResponsiblesToggleButton.setLayoutY(50);

        //set the "active" togglebutton to be selected at first
        inActiveCourseResponsiblesToggleButton.setSelected(true);

        //layouting buttons these buttons y position is dependant on the window size so it will be set in the updateLayout
        //method
        addNewCourseResponsibleButton.setLayoutX(10);
        removeCourseResponsibleButton.setLayoutX(10);

        //set the width of the sidemenu elements. They are set here and not in the updateLayout method, since their width
        //is not dependant on the size of the window
        inActiveCourseResponsiblesToggleButton.setPrefWidth(100);
        activeCourseResponsiblesToggleButton.setPrefWidth(100);

        addNewCourseResponsibleButton.setPrefWidth(80);
        removeCourseResponsibleButton.setPrefWidth(80);

        //styling
        this.setStyle(ACertsColorScheme.viewColor());

        inActiveCourseResponsiblesToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());
        activeCourseResponsiblesToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());

        addNewCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());
        removeCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());


        //functinality

    }

    @Override
    public void updateLayout()
    {
        //sets the editorviews size to be inside the current window with a little spacing added. 310 is the size of
        //the editor sidemenu (300) plus a margin of 10.
        courseResponsiblesTableView.setPrefWidth(this.getScene().getWidth() - 110);

        //60 represents a margin of 10 on top and bottom and an estimated height of 40 of the tabpane
        courseResponsiblesTableView.setPrefHeight(this.getScene().getHeight() - 60);

        //set the addNewCourses button to be above the bottom of the window. 60 represents a margin of 10 from the removebutton and
        // bottom and an estimated height of 40 of the tabpane
        addNewCourseResponsibleButton.setLayoutY(this.getScene().getHeight() - addNewCourseResponsibleButton.getHeight() - removeCourseResponsibleButton.getHeight() - 60);

        //set the addNewCourses button to be above the bottom of the window. 50 represents a margin of 10 from the
        // bottom and an estimated height of 40 of the tabpane
        removeCourseResponsibleButton.setLayoutY(this.getScene().getHeight() - removeCourseResponsibleButton.getHeight() - 50);

        addNewCourseResponsibleButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Add new Course Responsible");
            PopUpAddCourseResponsible popupPane = new PopUpAddCourseResponsible();
            popup.setScene(new Scene(popupPane,  500, 400));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });
    }
}
