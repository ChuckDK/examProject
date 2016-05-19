package view.courseresponsibles;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import view.styling.ACertsColorScheme;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpCourseResponsibleSelection extends Pane {
    private Button addNewCourseResponsibleButton;
    private Button chooseExistingCourseResponsibleButton;
    private Label instructions;

    public PopUpCourseResponsibleSelection()
    {
        //Create the two buttons the label.
        addNewCourseResponsibleButton = new Button("New Course Responsible");
        chooseExistingCourseResponsibleButton = new Button("Existing Course Responsible");
        instructions = new Label("Please select whether you want an existing or to add a new course responsible");

        //Set addNewCourseResponsibleButton's coordinates and size.
        addNewCourseResponsibleButton.setLayoutX(270);
        addNewCourseResponsibleButton.setLayoutY(50);
        addNewCourseResponsibleButton.setPrefSize(200, 100);

        //Set chooseExistingCourseResponsibleButton's coordinates and size.
        chooseExistingCourseResponsibleButton.setLayoutX(50);
        chooseExistingCourseResponsibleButton.setLayoutY(50);
        chooseExistingCourseResponsibleButton.setPrefSize(200, 100);

        //Set instructions label's coordinates.
        instructions.setLayoutX(50);
        instructions.setLayoutY(180);

        //Styling
        chooseExistingCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());
        addNewCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());

        //Add it all to the pane.
        this.getChildren().addAll(chooseExistingCourseResponsibleButton, addNewCourseResponsibleButton, instructions);
    }

}
