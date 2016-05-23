package view.courses;

import control.operations.MySQLCourses;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.coursedata.Course;
import view.styling.ACertsColorScheme;

import java.util.ArrayList;
import java.util.Calendar;

public class PopUpCourseSelectionList extends Pane {
    private TableView<Course> courseTableView;
    private Label titleLabel;
    private Button selectCourseButton;
    private Button cancelButton;

    public PopUpCourseSelectionList()
    {
        //Create the table view, the label, and the two buttons.
        courseTableView = new TableView<>();
        titleLabel = new Label("Choose a Course");
        selectCourseButton = new Button("Select");
        cancelButton = new Button("Cancel");

        //show courses in tableView
        ObservableList<Course> courses = FXCollections.observableArrayList(MySQLCourses.getAll());
        courseTableView.itemsProperty().setValue(courses);

        //Create the two columns needed.
        TableColumn<Course, String> courseNameColumn = new TableColumn<>("Course name");
        TableColumn<Course, Calendar> courseStartDateColumn = new TableColumn<>("Start date");
        TableColumn<Course, Calendar> courseEndDateColumn = new TableColumn<>("End date");

        //Set the columns equal to the properties of the courseResponsible objects.
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        courseEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));


        // Remove the empty column added by default
        courseTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Set the courseTableView's position in the pane.
        courseTableView.setLayoutY(50);
        courseTableView.setPrefHeight(280);

        //Set the selectCourseButton's position in the pane.
        selectCourseButton.setLayoutX(60);
        selectCourseButton.setLayoutY(340);

        //Set the cancelButton's position in the pane.
        //This button's function is defined in PopUpCourseResponsibleSelection.
        cancelButton.setLayoutX(130);
        cancelButton.setLayoutY(340);

        //Set the titleLabel's position in the pane.
        titleLabel.setLayoutX(40);
        titleLabel.setLayoutY(15);

        //Styling
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        selectCourseButton.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());

        //Add the columns to the table view.
        courseTableView.getColumns().addAll(
                courseNameColumn,
                courseStartDateColumn,
                courseEndDateColumn);

        //Add all the newly created and customized nodes to the class' pane.
        this.getChildren().addAll(cancelButton, courseTableView, titleLabel, selectCourseButton);
    }
}
