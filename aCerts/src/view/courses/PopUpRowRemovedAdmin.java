package view.courses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import view.styling.ACertsColorScheme;

public class PopUpRowRemovedAdmin extends Pane {
    private Label courseIDLabel;
    private Label courseNameLabel;
    private Label messageLabel;
    private Button okayButton;

    public PopUpRowRemovedAdmin(String removedElement, String type) {

        // Create the 3 Labels and the Okay Button.
        courseIDLabel = new Label(removedElement);
        courseNameLabel = new Label("");
        messageLabel = new Label(type+" successfully removed");
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

        //Styling
        okayButton.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());
    }

    // Method ot help center our labels
    public void updateLabelPosition(Label lbl){
        float width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(lbl.getText(), lbl.getFont());
        lbl.setLayoutX(250-width/2);
    }

}
