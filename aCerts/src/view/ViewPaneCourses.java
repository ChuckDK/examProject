package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.Course;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneCourses extends Pane implements Resizable
{
    //pane elements are defined here
    private TableView<Course> tableView;
    private ToggleButton active = new ToggleButton("Active");
    private ToggleButton inactive = new ToggleButton("Inactive");
    private ToggleButton all = new ToggleButton("All");
    private ToggleButton missing = new ToggleButton("Missing Certs");
    private Button addCourse = new Button("Add New");

    public ViewPaneCourses()
    {
        //create courses objects from mysql database and add them to temporary arraylist
        //for now just a dummy object

        Course dummy = new Course("machine code", "John Wick",  Calendar.getInstance(), Calendar.getInstance(), new Button(), new Button());
        ArrayList<Course> coursesArray= new ArrayList<>();
        coursesArray.add(dummy);

        //create an observablelist from our arraylist and create tableview

        ObservableList<Course> courses = FXCollections.observableArrayList(coursesArray);
        tableView = new TableView<>();
        tableView.itemsProperty().setValue(courses);

        //create columns for our tableview

        TableColumn<Course, String> courseNameColumn = new TableColumn<>("Course");
        TableColumn<Course, String> courseResponsibleColumn = new TableColumn<>("Course Responsible");
        TableColumn<Course, Calendar> startDateColumn = new TableColumn<>("Start Date");
        TableColumn<Course, Calendar> endDateColumn = new TableColumn<>("End Date");
        TableColumn<Course, Button> downloadColumn = new TableColumn<>("Test");
        TableColumn<Course, Button> viewParticipantsColumn = new TableColumn<>("Test");

        //link columns to values in our Course object

        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseResponsibleColumn.setCellValueFactory(new PropertyValueFactory<>("courseResponsible"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        downloadColumn.setCellValueFactory(new PropertyValueFactory<>("downloadButton"));
        viewParticipantsColumn.setCellValueFactory(new PropertyValueFactory<>("viewParticipants"));


        //customized cellfactory to show Calendar objects in tableview properly
        startDateColumn.setCellFactory(col -> new TableCell<Course, Calendar>()
        {
            public void updateItem(Calendar item, boolean empty)
            {
                super.updateItem(item, empty);
                if (item == null)
                {
                    setText(null);
                }
                else
                {
                    setText(""+item.get(+Calendar.DATE)+"/"+item.get(Calendar.MONTH)+"-"+item.get(Calendar.YEAR));
                }
            }
        });

        //customized cellfactory to show Calendar objects in tableview properly
        endDateColumn.setCellFactory(col -> new TableCell<Course, Calendar>()
        {
            public void updateItem(Calendar item, boolean empty)
            {
                super.updateItem(item, empty);
                if (item == null)
                {
                    setText(null);
                }
                else
                {
                    setText(""+item.get(+Calendar.DATE)+"/"+item.get(Calendar.MONTH)+"-"+item.get(Calendar.YEAR));
                }
            }
        });


        //add the columns to our tableView
        tableView.getColumns().addAll(
                courseNameColumn,
                courseResponsibleColumn,
                startDateColumn,
                endDateColumn,
                downloadColumn,
                viewParticipantsColumn);

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
                addCourse);


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

        //set the width of the sidemenu elements. They are set here and not in the updateLayout method, since their width
        //is not dependant on the size of the window
        active.setPrefWidth(80);
        inactive.setPrefWidth(80);
        all.setPrefWidth(80);
        missing.setPrefWidth(80);

        addCourse.setPrefWidth(80);

        //styling
        this.setStyle(ACertsColorScheme.sideMenuColor());
    }

    //methods that modifies the layout of certain elements to match with the current size of the window.
    @Override
    public void updateLayout()
    {
        //sets the editorviews size to be inside the current window with a little spacing added. 310 is the size of
        //the editor sidemenu (300) plus a margin of 10.
        tableView.setPrefWidth(this.getScene().getWidth() - 110);

        //60 represents a margin of 10 on top and bottom and an estimated height of 40 of the tabpane
        tableView.setPrefHeight(this.getScene().getHeight() - 60);

        //set the addCourse button to be above the bottom of the window. 60 represents a margin of
        // 10 from the bottom and an estimated height of 40 of the tabpane
        addCourse.setLayoutY(this.getScene().getHeight() - addCourse.getHeight() - 50);
    }
}
