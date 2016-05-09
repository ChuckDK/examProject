package GUI.TablePanes;

import GUI.Colors.Colors;
import GUI.Interfaces.Resizable;
import Model.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by dennis on 5/7/16.
 */
public class CoursesView extends Pane implements Resizable
{
    //pane elements are definde here
    private TableView<Course> tableView;
    private ToggleButton active = new ToggleButton("Active");
    private ToggleButton inactive = new ToggleButton("Inactive");
    private ToggleButton all = new ToggleButton("All");
    private ToggleButton missing = new ToggleButton("Missing Certs");
    private Button removeCourse = new Button("Remove");
    private Button addCourse = new Button("Add New");

    public CoursesView()
    {
        //create courses objects from mysql database and add them to temporary arraylist
        //for now just a dummy object

        Course dummy = new Course("machine code", "John Wick",  "24/12-0000", "11/9-1945");
        ArrayList<Course> coursesArray= new ArrayList<>();
        coursesArray.add(dummy);

        //create an observablelist from our arraylist and create tableview

        ObservableList<Course> courses = FXCollections.observableArrayList(coursesArray);
        tableView = new TableView<>();
        tableView.itemsProperty().setValue(courses);

        //create columns for our tableview

        TableColumn<Course, String> courseNameColumn = new TableColumn<>("Course");
        TableColumn<Course, String> courseResponsibleColumn = new TableColumn<>("Course Responsible");
        TableColumn<Course, String> startDateColumn = new TableColumn<>("Start Date");
        TableColumn<Course, String> endDateColumn = new TableColumn<>("End Date");
        TableColumn<Course, Button> testColumn = new TableColumn<>("Test");

        //link columns to values in our Course object

        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseResponsibleColumn.setCellValueFactory(new PropertyValueFactory<>("courseResponsible"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        testColumn.setCellValueFactory(new PropertyValueFactory<>("test"));

        courseNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        courseResponsibleColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        startDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        endDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());


        //add the columns to our tableView
        tableView.getColumns().addAll(courseNameColumn, courseResponsibleColumn, startDateColumn, endDateColumn);

        //group togglebuttons
        ToggleGroup group = new ToggleGroup();
        active.setToggleGroup(group);
        inactive.setToggleGroup(group);
        all.setToggleGroup(group);
        missing.setToggleGroup(group);

        //add elements to view
        this.getChildren().addAll(
                tableView,
                active,
                all,
                inactive,
                missing,
                addCourse,
                removeCourse);


        //layouting togglebuttons
        tableView.setLayoutX(100);
        tableView.setLayoutY(10);

        active.setLayoutX(10);
        active.setLayoutY(10);

        inactive.setLayoutX(10);
        inactive.setLayoutY(40);

        all.setLayoutX(10);
        all.setLayoutY(70);

        missing.setLayoutX(10);
        missing.setLayoutY(100);

        //set the "active" togglebutton to be selected at first
        active.setSelected(true);

        //layouting buttons these buttons y position is dependant on the window size so it will be set in the updateLayout
        //method
        addCourse.setLayoutX(10);
        removeCourse.setLayoutX(10);

        //set the width of the sidemenu elements. They are set here and not in the updateLayout method, since their width
        //is not dependant on the size of the window
        active.setPrefWidth(80);
        inactive.setPrefWidth(80);
        all.setPrefWidth(80);
        missing.setPrefWidth(80);

        addCourse.setPrefWidth(80);
        removeCourse.setPrefWidth(80);

        //styling
        this.setStyle(Colors.sideMenuColor());
    }

    //methods that modifies the layout of certain elements to match with the current size of the window.
    @Override
    public void updateLayout()
    {
        //sets the editorviews size to be inside the current window with a little spacing added. 310 is the size of
        //the editor sidemenu (300) plus a margin of 10.
        tableView.setPrefWidth(this.getScene().getWidth() - 110);

        //20 represents a margin of 10 at the top and at the bottom
        tableView.setPrefHeight(this.getScene().getHeight() - 20);

        //set the y-position of the removeCourse 30 pixels from the button. The 10 pixels are for the margin
        removeCourse.setLayoutY(this.getScene().getHeight() - removeCourse.getHeight() - 10);

        //set the y-position of the addCourse button 10 pixels above the removeCourse button. 10 of the pixels are the
        //space between the bottom of the window
        addCourse.setLayoutY(this.getScene().getHeight() - addCourse.getHeight() - removeCourse.getHeight() - 20);
    }
}
