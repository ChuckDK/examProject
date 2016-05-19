package view.courses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpCourseRemovedAdmin extends Pane {
    private Label courseIDLabel;
    private Label courseNameLabel;
    private Label messageLabel;
    private Button okayButton;

    public PopUpCourseRemovedAdmin() {

        // Create the 3 Labels and the Okay Button.
        courseIDLabel = new Label("Course ID");
        courseNameLabel = new Label("Course Name");
        messageLabel = new Label("Message Label");
        okayButton = new Button("Okay");

        // Set position for CourseID.
        courseIDLabel.setLayoutY(100);

        // Set position for CourseName.
        courseNameLabel.setLayoutY(150);

        // Set position for MessageLabel.
        messageLabel.setLayoutY(200);


        // Set position for Okay button.
        okayButton.setLayoutX(225);
        okayButton.setLayoutY(300);

        //Add all the newly created nodes to the pane.
        this.getChildren().addAll(courseIDLabel, courseNameLabel, messageLabel, okayButton);

        updateLabelPosition(courseIDLabel);
        updateLabelPosition(courseNameLabel);
        updateLabelPosition(messageLabel);
    }

    // Method ot help center our labels
    public void updateLabelPosition(Label lbl){
        float width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(lbl.getText(), lbl.getFont());
        lbl.setLayoutX(250-width/2);
    }

}
