package view.courses;

import control.operations.MySQLCourses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coursedata.Course;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

public class ViewPaneCourses extends Pane implements Resizable
{
    //Pane elements are defined here.
    protected TableView<Course> courseTableView;
    private ToggleButton active = new ToggleButton("Active");
    private ToggleButton inactive = new ToggleButton("Inactive");
    private ToggleButton all = new ToggleButton("All");
    private ToggleButton missing = new ToggleButton("Missing Certs");
    protected Button addNewCourses = new Button("Add new");

    public ViewPaneCourses()
    {
        //Create a new table view calling it courseTableView.
        courseTableView = new TableView<>();

        //Create a JavaFX observable array list.
        //Since the toggle button 'active' is toggle from the start, the table view starts of being filled with
        //active course responsibles.
        ObservableList<Course> coursesArray = FXCollections.observableArrayList(MySQLCourses.getActive());
        courseTableView.itemsProperty().setValue(coursesArray);

        //Create columns for our table view.
        TableColumn<Course, String> courseNameColumn = new TableColumn<>("Course");
        TableColumn<Course, String> courseResponsibleColumn = new TableColumn<>("Course Responsible");
        TableColumn<Course, String> startDateColumn = new TableColumn<>("Start Date");
        TableColumn<Course, String> endDateColumn = new TableColumn<>("End Date");
        TableColumn<Course, Button> downloadColumn = new TableColumn<>("Test");
        TableColumn<Course, Button> viewParticipantsColumn = new TableColumn<>("Test");

        //Link the columns to the values in our Course object.
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseResponsibleColumn.setCellValueFactory(new PropertyValueFactory<>("courseResponsible"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        downloadColumn.setCellValueFactory(new PropertyValueFactory<>("downloadButton"));
        viewParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("viewParticipants"));

        //Add the columns to our courseTableView
        courseTableView.getColumns().addAll(
                courseNameColumn,
                courseResponsibleColumn,
                startDateColumn,
                endDateColumn,
                downloadColumn,
                viewParticipantsColumn);

        ////Group toggle buttons using JavaFX ToggleGroup.
        ToggleGroup group = new ToggleGroup();
        active.setToggleGroup(group);
        inactive.setToggleGroup(group);
        all.setToggleGroup(group);
        missing.setToggleGroup(group);

        //Add elements to the instance of the class.
        this.getChildren().addAll(
                active,
                all,
                inactive,
                missing,
                addNewCourses,
                courseTableView);


        //Set the position of the table view, and the toggle buttons using setLayoutX and setLayoutY.
        courseTableView.setLayoutX(100);
        courseTableView.setLayoutY(10);

        active.setLayoutX(10);
        active.setLayoutY(20);

        inactive.setLayoutX(10);
        inactive.setLayoutY(50);

        all.setLayoutX(10);
        all.setLayoutY(80);

        missing.setLayoutX(10);
        missing.setLayoutY(110);

        //Set the "active" toggle button to be selected at first.
        active.setSelected(true);

        //Set the position of the addNewCourses button.
        //This button's Y position is dependant on the window's size so the Y position
        //will be set in the updateLayout method.
        addNewCourses.setLayoutX(10);

        //Set the width of the side menu elements.
        //They are set here and not in the updateLayout method, since their width
        //is not dependant on the size of the window.
        active.setPrefWidth(100);
        inactive.setPrefWidth(100);
        all.setPrefWidth(100);
        missing.setPrefWidth(100);

        addNewCourses.setPrefWidth(80);

        //Style all elements using ACertsColorScheme.
        this.setStyle(ACertsColorScheme.viewColor());

        active.setStyle(ACertsColorScheme.toggleButtonColor());
        inactive.setStyle(ACertsColorScheme.toggleButtonColor());
        all.setStyle(ACertsColorScheme.toggleButtonColor());
        missing.setStyle(ACertsColorScheme.toggleButtonColor());

        addNewCourses.setStyle(ACertsColorScheme.buttonColor());

        //Add functionality to the addNewCourses button.
        addNewCourses.setOnAction(e->
        {
            //Create a popup window using an instance of the PopUpAddCourse class.
            //See the class for it's content.
            Stage popup = new Stage();
            popup.setTitle("Add new course");
            PopUpAddCourse popupPane = new PopUpAddCourse();
            popup.setScene(new Scene(popupPane,  500, 400));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });

        inactive.setOnAction(event ->
        {
            ObservableList<Course> courses = FXCollections.observableArrayList(MySQLCourses.getInActive());
            courseTableView.itemsProperty().setValue(courses);
        });

        active.setOnAction(event ->
        {
            ObservableList<Course> courses = FXCollections.observableArrayList(MySQLCourses.getActive());
            courseTableView.itemsProperty().setValue(courses);
        });

        all.setOnAction(event ->
        {
            ObservableList<Course> courses = FXCollections.observableArrayList(MySQLCourses.getAll());
            courseTableView.itemsProperty().setValue(courses);
        });
    }

    //methods that modifies the layout of certain elements to match with the current size of the window.
    @Override
    public void updateLayout()
    {
        //sets the editorviews size to be inside the current window with a little spacing added. 310 is the size of
        //the editor sidemenu (300) plus a margin of 10.
        courseTableView.setPrefWidth(this.getScene().getWidth() - 110);

        //60 represents a margin of 10 on top and bottom and an estimated height of 40 of the tabpane
        courseTableView.setPrefHeight(this.getScene().getHeight() - 60);

        //set the addNewCourses button to be above the bottom of the window. 60 represents a margin of
        // 10 from the bottom and an estimated height of 40 of the tabpane
        addNewCourses.setLayoutY(this.getScene().getHeight() - addNewCourses.getHeight() - 50);
    }
}
