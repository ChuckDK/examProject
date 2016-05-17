package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpCourseResponsibleSelection extends Pane {
    private Button addNewCourseResponsibleButton;
    private Button chooseExistingCourseResponsibleButton;
    private Label instructions;

    public PopUpCourseResponsibleSelection()
    {
        //Creating the two buttons the label.
        addNewCourseResponsibleButton = new Button("New Course Responsible");
        chooseExistingCourseResponsibleButton = new Button("Existing Course Responsible");
        instructions = new Label("Please select whether you want an existing or to add a new course responsible");

        //Setting addNewCourseResponsibleButton's coordinates and size.
        addNewCourseResponsibleButton.setLayoutX(50);
        addNewCourseResponsibleButton.setLayoutY(50);
        addNewCourseResponsibleButton.setPrefSize(200, 100);

        //Setting chooseExistingCourseResponsibleButton's coordinates and size.
        chooseExistingCourseResponsibleButton.setLayoutX(270);
        chooseExistingCourseResponsibleButton.setLayoutY(50);
        chooseExistingCourseResponsibleButton.setPrefSize(200, 100);

        //Setting instructions label's coordinates.
        instructions.setLayoutX(50);
        instructions.setLayoutY(180);

        //Adding it all to the pane.
        this.getChildren().addAll(addNewCourseResponsibleButton, chooseExistingCourseResponsibleButton, instructions);
    }

}
