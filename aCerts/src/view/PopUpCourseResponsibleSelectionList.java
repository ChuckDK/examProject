package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Course;
import model.CourseResponsible;

import java.util.ArrayList;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpCourseResponsibleSelectionList extends Pane {
    private TableView<CourseResponsible> courseResponsiblesTableView;
    private Label titleLabel;
    private Button selectCourseResponsibleButton;
    private Button cancelButton;

    public PopUpCourseResponsibleSelectionList()
    {
        //Create the table view, the label, and the two buttons.
        courseResponsiblesTableView = new TableView<>();
        titleLabel = new Label("Choose a Course Responsible");
        selectCourseResponsibleButton = new Button("Select");
        cancelButton = new Button("Cancel");

        //Create a dummy course responsible object.
        CourseResponsible dummyCourseResponsible = new CourseResponsible("John", "Travolta", "JohnTravolta@hotmail.com",
                "42066669");

        //Create an array list for course responsibles.
        ArrayList<CourseResponsible> courseResponsibleArray = new ArrayList<>();

        //Add the dummy course responsible to the array list of course responsibles.
        courseResponsibleArray.add(dummyCourseResponsible);

        //Make the array list of course responsibles an observable JavaFX array list.
        ObservableList<CourseResponsible> courseResponsibles =
                FXCollections.observableArrayList(courseResponsibleArray);

        //Connect the values of the table view to the observable array list.
        courseResponsiblesTableView.itemsProperty().setValue(courseResponsibles);

        //Create the two columns needed.
        TableColumn<CourseResponsible, String> courseResponsibleFirstNameColumn = new TableColumn<>("First Name");
        TableColumn<CourseResponsible, String> courseResponsibleLastNameColumn = new TableColumn<>("Last Name");

        //Set the columns equal to the properties of the courseResponsible objects.
        courseResponsibleFirstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        courseResponsibleLastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        // Remove the empty column added by default
        courseResponsiblesTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        //Set the courseResponsiblesTableView's position in the pane.
        courseResponsiblesTableView.setLayoutY(50);
        courseResponsiblesTableView.setPrefHeight(280);

        //Set the selectCourseResponsibleButton's position in the pane.
        selectCourseResponsibleButton.setLayoutX(60);
        selectCourseResponsibleButton.setLayoutY(340);

        //Set the cancelButton's position in the pane.
        //This button's function is defined in PopUpCourseResponsibleSelection.
        cancelButton.setLayoutX(130);
        cancelButton.setLayoutY(340);

        //Set the titleLabel's position in the pane.
        titleLabel.setLayoutX(40);
        titleLabel.setLayoutY(15);

        //Add the columns to the table view.
        courseResponsiblesTableView.getColumns().addAll(courseResponsibleFirstNameColumn,
                courseResponsibleLastNameColumn);

        //Add all the newly created and customized nodes to the class' pane.
        this.getChildren().addAll(courseResponsiblesTableView, titleLabel, selectCourseResponsibleButton, cancelButton);
    }
}
