package view;

import javafx.scene.control.Button;

/**
 * Created by dennis on 5/13/16.
 */
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
        addCourseResponsibleButton.setStyle(ACertsColorScheme.buttonBcolor());
    }
}
