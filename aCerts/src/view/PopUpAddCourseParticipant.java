package view;

import javafx.scene.control.Button;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpAddCourseParticipant extends PopUpAddCoursePersona {
    private Button addCourseParticipant;

    public PopUpAddCourseParticipant()
    {
        super();
        addCourseParticipant = new Button("Add course participant");

        addCourseParticipant.setLayoutX(30);
        addCourseParticipant.setLayoutY(350);

        this.getChildren().addAll(addCourseParticipant);

        //styling
        addCourseParticipant.setStyle(ACertsColorScheme.buttonColor());
    }
}
