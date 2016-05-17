package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CourseResponsible;

import java.util.ArrayList;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneCourseResponsiblesAdmin extends Pane implements Resizable {

    private TableView<CourseResponsible> courseResponsiblesTableView;
    private ToggleButton inActiveCourseResponsiblesToggleButton;
    private ToggleButton activeCourseResponsiblesToggleButton;
    private Button removeCourseResponsibleButton;
    private Button addNewCourseResponsibleButton;

    public ViewPaneCourseResponsiblesAdmin()
    {
        //create courses objects from mysql database and add them to temporary arraylist
        //for now just a dummy object

        CourseResponsible dummy = new CourseResponsible("Signe", "Something", "12345678", "ss@yahoo.dk");
        ArrayList<CourseResponsible> coursesArray= new ArrayList<>();
        coursesArray.add(dummy);

        //create an observablelist from our arraylist and create tableview

        ObservableList<CourseResponsible> courses = FXCollections.observableArrayList(coursesArray);
        courseResponsiblesTableView = new TableView<>();
        courseResponsiblesTableView.itemsProperty().setValue(courses);

        //create columns for our tableview

        TableColumn<CourseResponsible, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<CourseResponsible, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<CourseResponsible, String> emailColumn = new TableColumn<>("Email");
        TableColumn<CourseResponsible, String> phoneNumberColumn = new TableColumn<>("Phone Number");

        //link columns to values in our Course object

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


        //add the columns to our courseTableView
        courseResponsiblesTableView.getColumns().addAll(
                firstNameColumn,
                lastNameColumn,
                emailColumn,
                phoneNumberColumn);

        //group togglebuttons
        ToggleGroup group = new ToggleGroup();

        inActiveCourseResponsiblesToggleButton = new ToggleButton("Missing");
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

        addNewCourseResponsibleButton.setStyle(ACertsColorScheme.buttonBcolor());
        removeCourseResponsibleButton.setStyle(ACertsColorScheme.buttonBcolor());


        //functinality
        addNewCourseResponsibleButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Add new course responsible");
            PopUpCourseResponsibleSelection popupPane = new PopUpCourseResponsibleSelection();
            popup.setScene(new Scene(popupPane, 500, 400));

            popup.initModality(Modality.APPLICATION_MODAL);

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex -> {
                Stage stage = new Stage();
                Pane pane = new PopUpCourseResponsibleSelectionList();
                Scene scene = new Scene(pane, 500, 400);
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);

                //This line enables functionality for the 'cancelButton' in the 'PopUpCourseResponsibleSelectionList' class
                //hence the number  which refers to the 0-indexed number where the 'cancelButton' is added.
                ((Button) pane.getChildren().get(0)).setOnAction(ev -> stage.close());
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

    }
}
