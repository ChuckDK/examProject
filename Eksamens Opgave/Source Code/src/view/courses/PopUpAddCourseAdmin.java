package view.courses;

import javafx.scene.control.Button;
import view.styling.ACertsColorScheme;

public class PopUpAddCourseAdmin extends PopUpAddCourse {
    private Button addCourseResponsibleButton;

    public PopUpAddCourseAdmin()
    {
        //Call the PopUpAddCoursePersona class to get the GUI for the class.
        super();

        //Initialize the addCourseResponsibleButton button.
        addCourseResponsibleButton = new Button("Add course responsible");

        //Set the addCourseResponsibleButton button's position.
        addCourseResponsibleButton.setLayoutX(270);
        addCourseResponsibleButton.setLayoutY(140);

        //Add the buttons to the instance of the class.
        this.getChildren().addAll(addCourseResponsibleButton, assignCourseResponsible);

        //Style the addCourseResponsibleButton with ACertsColorScheme.
        addCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());
    }
}
