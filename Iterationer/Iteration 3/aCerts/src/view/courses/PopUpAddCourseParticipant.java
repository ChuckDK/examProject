package view.courses;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.styling.ACertsColorScheme;

public class PopUpAddCourseParticipant extends PopUpAddCoursePersona {
    private Button addCourseParticipant;
    private Button assignCourse;

    public PopUpAddCourseParticipant()
    {
        super();
        addCourseParticipant = new Button("Add course participant");
        assignCourse = new Button("Assign Course");

        addCourseParticipant.setLayoutX(30);
        addCourseParticipant.setLayoutY(350);

        assignCourse.setLayoutX(270);
        assignCourse.setLayoutY(140);

        assignCourse.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Select Course");
            PopUpCourseSelectionList popupPane = new PopUpCourseSelectionList();
            popup.setScene(new Scene(popupPane));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });

        this.getChildren().addAll(addCourseParticipant, assignCourse);

        //styling
        addCourseParticipant.setStyle(ACertsColorScheme.buttonColor());
        assignCourse.setStyle(ACertsColorScheme.buttonColor());
    }
}
