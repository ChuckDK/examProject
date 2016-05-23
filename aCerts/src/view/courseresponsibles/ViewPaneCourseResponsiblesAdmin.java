package view.courseresponsibles;

import control.operations.MySQLCourseResponsible;
import control.operations.SQLOperations;
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

public class ViewPaneCourseResponsiblesAdmin extends Pane implements Resizable
{
    private TableView<CourseResponsible> courseResponsiblesTableView;
    private ToggleButton inActiveCourseResponsiblesToggleButton;
    private ToggleButton activeCourseResponsiblesToggleButton;
    private ToggleButton allCourseResponsiblesToggleButton;
    private Button removeCourseResponsibleButton;
    private Button addNewCourseResponsibleButton;

    public ViewPaneCourseResponsiblesAdmin()
    {
        //Create a table view.
        courseResponsiblesTableView = new TableView<>();

        //Create a JavaFX observable array list.
        //Since the toggle button 'active' is toggle from the start, the table view starts of being filled with
        //active course responsibles.
        ObservableList<CourseResponsible> initCourseResponsibles = FXCollections.observableArrayList(MySQLCourseResponsible.getActive());

        //Binding the observable array list to the table view.
        courseResponsiblesTableView.itemsProperty().setValue(initCourseResponsibles);

        //Create columns for our table view.
        TableColumn<CourseResponsible, String> firstNameColumn = new TableColumn<>("First Name");
        TableColumn<CourseResponsible, String> lastNameColumn = new TableColumn<>("Last Name");
        TableColumn<CourseResponsible, String> emailColumn = new TableColumn<>("Email");
        TableColumn<CourseResponsible, String> phoneNumberColumn = new TableColumn<>("Phone Number");
        TableColumn<CourseResponsible, String> phoneNumber2Column = new TableColumn<>("Phone Number 2");

        //Link columns to values in our Course object.
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        phoneNumber2Column.setCellValueFactory(new PropertyValueFactory<>("phoneNumber2"));


        //Add the columns to the table view.
        courseResponsiblesTableView.getColumns().addAll(
                firstNameColumn,
                lastNameColumn,
                emailColumn,
                phoneNumberColumn,
                phoneNumber2Column);

        //Group toggle buttons using JavaFX ToggleGroup.
        ToggleGroup group = new ToggleGroup();

        //Create the toggle buttons.
        inActiveCourseResponsiblesToggleButton = new ToggleButton("Inactive");
        activeCourseResponsiblesToggleButton = new ToggleButton("Active");
        allCourseResponsiblesToggleButton = new ToggleButton("All");

        //Add the newly created toggle buttons to the toggle group.
        inActiveCourseResponsiblesToggleButton.setToggleGroup(group);
        activeCourseResponsiblesToggleButton.setToggleGroup(group);
        allCourseResponsiblesToggleButton.setToggleGroup(group);

        //Create the addNewCourseResponsibleButton button.
        addNewCourseResponsibleButton = new Button("Add new");

        //Create the removeCourseResponsibleButton button.
        removeCourseResponsibleButton = new Button("remove");

        //Add all elements to the instance of the class.
        this.getChildren().addAll(
                inActiveCourseResponsiblesToggleButton,
                activeCourseResponsiblesToggleButton,
                allCourseResponsiblesToggleButton,
                addNewCourseResponsibleButton,
                removeCourseResponsibleButton,
                courseResponsiblesTableView);


        //Set the position of the toggle buttons using setLayoutX and setLayoutY.
        courseResponsiblesTableView.setLayoutX(100);
        courseResponsiblesTableView.setLayoutY(10);

        inActiveCourseResponsiblesToggleButton.setLayoutX(10);
        inActiveCourseResponsiblesToggleButton.setLayoutY(50);

        activeCourseResponsiblesToggleButton.setLayoutX(10);
        activeCourseResponsiblesToggleButton.setLayoutY(20);

        allCourseResponsiblesToggleButton.setLayoutX(10);
        allCourseResponsiblesToggleButton.setLayoutY(80);

        //Set the "active" toggle button to be selected at first.
        activeCourseResponsiblesToggleButton.setSelected(true);

        //Set the position of the buttons.
        //These buttons Y position is dependant on the window's size so the Y position
        //will be set in the updateLayout method.
        addNewCourseResponsibleButton.setLayoutX(10);
        removeCourseResponsibleButton.setLayoutX(10);

        //Set the width of the side menu elements.
        //They are set here and not in the updateLayout method, since their width
        //is not dependant on the size of the window.
        inActiveCourseResponsiblesToggleButton.setPrefWidth(100);
        activeCourseResponsiblesToggleButton.setPrefWidth(100);
        allCourseResponsiblesToggleButton.setPrefWidth(100);

        addNewCourseResponsibleButton.setPrefWidth(80);
        removeCourseResponsibleButton.setPrefWidth(80);

        //Styling the buttons using ACertsColorScheme.
        this.setStyle(ACertsColorScheme.viewColor());

        inActiveCourseResponsiblesToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());
        activeCourseResponsiblesToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());
        allCourseResponsiblesToggleButton.setStyle(ACertsColorScheme.toggleButtonColor());

        addNewCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());
        removeCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());


        //Add functionality to the activeCourseResponsiblesToggleButton button.
        activeCourseResponsiblesToggleButton.setOnAction(e->
        {
            //Same functionality as in line 35 and 38 in this class. See class 'MySQLCourseResponsible' in order to see
            //the 'getActive()' methods functionality.
            ObservableList<CourseResponsible> courseResponsibles = FXCollections.observableArrayList(MySQLCourseResponsible.getActive());
            courseResponsiblesTableView.itemsProperty().setValue(courseResponsibles);
        });

        //Add functionality to the allCourseResponsiblesToggleButton button.
        allCourseResponsiblesToggleButton.setOnAction(e->
        {
            //Same functionality as in line 35 and 38 in this class. See class 'MySQLCourseResponsible' in order to see
            //the 'getAll()' methods functionality.
            ObservableList<CourseResponsible> courseResponsibles = FXCollections.observableArrayList(MySQLCourseResponsible.getAll());
            courseResponsiblesTableView.itemsProperty().setValue(courseResponsibles);
        });

        //Add functionality to the inActiveCourseResponsiblesToggleButton button.
        inActiveCourseResponsiblesToggleButton.setOnAction(e->
        {
            //Same functionality as in line 35 and 38 in this class. See class 'MySQLCourseResponsible' in order to see
            //'getInActive()' methods functionality.
            ObservableList<CourseResponsible> courseResponsibles = FXCollections.observableArrayList(MySQLCourseResponsible.getInActive());
            courseResponsiblesTableView.itemsProperty().setValue(courseResponsibles);
        });

        //Add functionality to the addNewCourseResponsibleButton button.
        addNewCourseResponsibleButton.setOnAction(e->
        {
            //Create a popup window using an instance of the PopUpAddCourseResponsible class.
            //See the class for it's content.
            Stage popup = new Stage();
            popup.setTitle("Add new Course Responsible");
            PopUpAddCourseResponsible popupPane = new PopUpAddCourseResponsible();
            popup.setScene(new Scene(popupPane,  500, 400));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            ((Button) popupPane.getChildren().get(13)).setOnAction(ex->
            {
                if(popupPane.checkForValues())
                {
                    popup.close();
                }
            });

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });
    }

    //Override the updateLayout method from the Resizable interface to make
    //the ViewPaneCourseResponsiblesAdmin appear as it's class dictates.
    @Override
    public void updateLayout()
    {
        //Sets the table view called courseResponsiblesTableView's width to be equal to itself minus 110 pixels.
        //Subtracting 110 makes space for the side menu.
        courseResponsiblesTableView.setPrefWidth(this.getScene().getWidth() - 110);

        //Sets the table view called courseResponsiblesTableView's height to be equal to itself minus 60 pixels.
        //Subtracting 60 makes space for the tap pane at the top of the window.
        courseResponsiblesTableView.setPrefHeight(this.getScene().getHeight() - 60);

        //The addNewCourseResponsibleButton button doesn't need a setLayoutX as it is placed completely to the left.
        //It's Y coordinate however, is set to be directly below the removeCourseResponsibleButton button.
        addNewCourseResponsibleButton.setLayoutY(this.getScene().getHeight() -
                addNewCourseResponsibleButton.getHeight() - removeCourseResponsibleButton.getHeight() - 60);

        //The removeCourseResponsibleButton button doesn't need a setLayoutX as it is placed completely to the left.
        //It's Y coordinate however, is set to be directly above the addNewCourseResponsibleButton button.
        removeCourseResponsibleButton.setLayoutY(this.getScene().getHeight() -
                removeCourseResponsibleButton.getHeight() - 50);

    }
}
