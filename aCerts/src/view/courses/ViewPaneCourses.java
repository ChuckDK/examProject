package view.courses;

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

import java.util.ArrayList;
import java.util.Calendar;

public class ViewPaneCourses extends Pane implements Resizable
{
    //pane elements are defined here
    protected TableView<Course> courseTableView;
    private ToggleButton active = new ToggleButton("Active");
    private ToggleButton inactive = new ToggleButton("Inactive");
    private ToggleButton all = new ToggleButton("All");
    private ToggleButton missing = new ToggleButton("Missing Certs");
    protected Button addNewCourses = new Button("Add new");

    public ViewPaneCourses()
    {
        //create courses objects from mysql database and add them to temporary arraylist
        //for now just a dummy object

        Course dummy = new Course("machine code", "John Wick",  Calendar.getInstance(), Calendar.getInstance(), new Button(), new Button());
        ArrayList<Course> coursesArray= new ArrayList<>();
        coursesArray.add(dummy);

        //create an observablelist from our arraylist and create tableview

        ObservableList<Course> courses = FXCollections.observableArrayList(coursesArray);
        courseTableView = new TableView<>();
        courseTableView.itemsProperty().setValue(courses);

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


        //add the columns to our courseTableView
        courseTableView.getColumns().addAll(
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
                active,
                all,
                inactive,
                missing,
                addNewCourses,
                courseTableView);


        //layouting togglebuttons
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

        //set the "active" togglebutton to be selected at first
        active.setSelected(true);

        //layouting buttons these buttons y position is dependant on the window size so it will be set in the updateLayout
        //method
        addNewCourses.setLayoutX(10);

        //set the width of the sidemenu elements. They are set here and not in the updateLayout method, since their width
        //is not dependant on the size of the window
        active.setPrefWidth(100);
        inactive.setPrefWidth(100);
        all.setPrefWidth(100);
        missing.setPrefWidth(100);

        addNewCourses.setPrefWidth(80);

        //styling
        this.setStyle(ACertsColorScheme.viewColor());

        active.setStyle(ACertsColorScheme.toggleButtonColor());
        inactive.setStyle(ACertsColorScheme.toggleButtonColor());
        all.setStyle(ACertsColorScheme.toggleButtonColor());
        missing.setStyle(ACertsColorScheme.toggleButtonColor());

        addNewCourses.setStyle(ACertsColorScheme.buttonColor());

        //functionality
        addNewCourses.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Add new course");
            PopUpAddCourse popupPane = new PopUpAddCourse();
            popup.setScene(new Scene(popupPane,  500, 400));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
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
