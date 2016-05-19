package view.courses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.coursedata.Course;
import view.styling.ACertsColorScheme;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by dennis on 5/13/16.
 */
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

        //Create a dummy course responsible object.
        Course dummyCourse = new Course("John", "Travolta", Calendar.getInstance(), Calendar.getInstance(), new Button(),
                new Button());

        //Create an array list for course responsibles.
        ArrayList<Course> courseResponsibleArray = new ArrayList<>();

        //Add the dummy course responsible to the array list of course responsibles.
        courseResponsibleArray.add(dummyCourse);

        //Make the array list of course responsibles an observable JavaFX array list.
        ObservableList<Course> courses =
                FXCollections.observableArrayList(courseResponsibleArray);

        //Connect the values of the table view to the observable array list.
        courseTableView.itemsProperty().setValue(courses);

        //Create the two columns needed.
        TableColumn<Course, String> courseNameColumn = new TableColumn<>("Course name");
        TableColumn<Course, Calendar> courseStartDateColumn = new TableColumn<>("Start date");
        TableColumn<Course, Calendar> courseEndDateColumn = new TableColumn<>("End date");

        //Set the columns equal to the properties of the courseResponsible objects.
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        courseStartDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        courseEndDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        //customized cellfactory to show Calendar objects in tableview properly
        courseStartDateColumn.setCellFactory(col -> new TableCell<Course, Calendar>()
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
        courseEndDateColumn.setCellFactory(col -> new TableCell<Course, Calendar>()
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
