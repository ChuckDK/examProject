package view.courses;

import javafx.scene.control.Button;
import view.styling.ACertsColorScheme;

public class PopUpAddCourseAdmin extends PopUpAddCourse {
    private Button addCourseResponsibleButton;

    public PopUpAddCourseAdmin()
    {
        super();
        addCourseResponsibleButton = new Button("Add course responsible");

        addCourseResponsibleButton.setLayoutX(270);
        addCourseResponsibleButton.setLayoutY(140);

        this.getChildren().addAll(addCourseResponsibleButton);

        //styling
        addCourseResponsibleButton.setStyle(ACertsColorScheme.buttonColor());
    }
}
