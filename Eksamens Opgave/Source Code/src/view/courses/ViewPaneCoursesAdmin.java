package view.courses;

import control.operations.SQLOperations;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.styling.ACertsColorScheme;

public class ViewPaneCoursesAdmin extends ViewPaneCourses{

    private Button removeCourses;

    public ViewPaneCoursesAdmin()
    {
        //Call the superclass constructor so all the elements from that class can be added upon.
        super();

        //Initialize a remove button and set it's position and size.
        removeCourses = new Button("Remove");
        removeCourses.setLayoutX(10);
        removeCourses.setPrefWidth(80);

        //Add elements to the instance of the class.
        this.getChildren().addAll(removeCourses);

        //Styling the removeCourses button using ACertsColorScheme.
        removeCourses.setStyle(ACertsColorScheme.buttonColor());

        //replacing functionality so that the addcourse button now opens the admin version og the popup
        addNewCourses.setOnAction(e->
        {
            //Create a popup window using an instance of the PopUpAddCourseAdmin class.
            //See the class for it's content.
            Stage popup = new Stage();
            popup.setTitle("Add new course");
            PopUpAddCourseAdmin popupPane = new PopUpAddCourseAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            /* **functionality not implemented, can be done in a future update**
            ((Button) popupPane.getChildren().get(7)).setOnAction(ex->
            {
                FileChooser fileChooser = new FileChooser();

                File file = fileChooser.showOpenDialog(new Stage());

                file.renameTo(new File(file.getAbsolutePath()+((TextField) popupPane.getChildren().get(2)).getText()));

                FTPOperations.uploadFile(file);
            });*/

            ((Button) popupPane.getChildren().get(7)).setOnAction(ex->
            {
                if(popupPane.checkForValues())
                {
                    popup.close();
                }
            });


            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });

        removeCourses.setOnAction(event ->
        {
            //Create a popup window using an instance of the PopUpAddCourseAdmin class.
            //See the class for it's content.
            Stage popup = new Stage();
            popup.setTitle("Remove Course");
            PopUpRemoveRowAdmin popupPane = new PopUpRemoveRowAdmin("Warning!! This is permanent and cannot be undone!" +
                    "\nPlease input a course ID for the course" +
                    "\nthat you want to remove", "Input course id...");
            popup.setScene(new Scene(popupPane,  460, 200));

            ((Button) popupPane.getChildren().get(3)).setOnAction(e -> {
                if(SQLOperations.removeRow("certificates", "course_id", ((TextField) popupPane.getChildren().get(1)).getText()) &&
                        SQLOperations.removeRow("courses", "course_id", ((TextField) popupPane.getChildren().get(1)).getText()))
                {
                    Stage stage = new Stage();
                    Pane pane = new PopUpRowRemovedAdmin(((TextField) popupPane.getChildren().get(1)).getText(), "Course");
                    Scene scene = new Scene(pane, 300, 120);
                    stage.setScene(scene);

                    //This line enables functionality for the 'okayButton' in the 'PopUpRowRemovedAdmin' class
                    //hence the number 3 which refers to the 0-indexed number where the 'okayButton' is added.
                    ((Button) pane.getChildren().get(2)).setOnAction(a -> stage.close());

                    popup.close();
                    stage.show();
                }
            });

            //close the window when cancel is pressed
            ((Button) popupPane.getChildren().get(0)).setOnAction(e -> {
                popup.close();
                    });


                popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });
    }

    //Override the updateLayout method from the Resizable interface to make
    //the ViewPaneCoursesAdmin appear as it's class dictates.
    @Override
    public void updateLayout()
    {
        //Sets the course table view's width to be equal to itself minus 110 pixels.
        //Subtracting 110 makes space for the side menu.
        courseTableView.setPrefWidth(this.getScene().getWidth() - 110);

        //Sets the course table view's height to be equal to itself minus 60 pixels.
        //Subtracting 60 makes space for the tap pane at the top of the window.
        courseTableView.setPrefHeight(this.getScene().getHeight() - 60);

        //The addNewCourses button doesn't need a setLayoutX as it is placed completely to the left.
        //It's Y coordinate however, is set to be directly below the removeCourses button.
        addNewCourses.setLayoutY(this.getScene().getHeight() - addNewCourses.getHeight() - removeCourses.getHeight() - 60);

        //The removeCourses button doesn't need a setLayoutX as it is placed completely to the left.
        //It's Y coordinate however, is set to be directly above the removeCourses button.
        removeCourses.setLayoutY(this.getScene().getHeight() - addNewCourses.getHeight() - 50);
    }
}

