package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpRemoveCourseAdmin extends Pane {
    private TextField courseIDTextField;
    private Label instructionsLabel;
    private Button confirmButton;
    private Button cancelButton;

    public PopUpRemoveCourseAdmin()
    {
        //Create the text field, the label, and the two buttons.
        cancelButton = new Button("Cancel");
        courseIDTextField = new TextField();
        instructionsLabel = new Label("Please enter Course ID for the course you want to " +
                "remove\n\t\t\t\t This is permanent");
        confirmButton = new Button("Confirm");

        //Set the position and the prompt text for the text field.
        courseIDTextField.setLayoutX(100);
        courseIDTextField.setLayoutY(100);
        courseIDTextField.setPromptText("Course ID");

        //Set the position for the instructionsLabel.
        instructionsLabel.setLayoutX(20);
        instructionsLabel.setLayoutY(50);

        //Set the position for the confirmButton.
        confirmButton.setLayoutX(80);
        confirmButton.setLayoutY(200);

        //Set the position for the cancelButton.
        cancelButton.setLayoutX(150);
        cancelButton.setLayoutY(200);

        //Add all the newly created nodes to the pane.
        this.getChildren().addAll(cancelButton, courseIDTextField, instructionsLabel, confirmButton);
    }
}
