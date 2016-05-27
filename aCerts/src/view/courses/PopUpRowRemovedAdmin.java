package view.courses;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import view.styling.ACertsColorScheme;

public class PopUpRowRemovedAdmin extends Pane {
    private Label courseIDLabel;
    private Label messageLabel;
    private Button okayButton;

    public PopUpRowRemovedAdmin(String removedElement, String type) {

        // Create the 3 Labels and the Okay Button.
        courseIDLabel = new Label(removedElement);
        messageLabel = new Label(type+" successfully removed");
        okayButton = new Button("Okay");

        // Set position for CourseID.
        courseIDLabel.setLayoutY(10);

        // Set position for MessageLabel.
        messageLabel.setLayoutY(40);


        // Set position for Okay button.
        okayButton.setLayoutX(105);
        okayButton.setLayoutY(80);

        //set width of the Okay button
        okayButton.setPrefWidth(90);

        //Add all the newly created nodes to the pane.
        this.getChildren().addAll(courseIDLabel, messageLabel, okayButton);

        updateLabelPosition(courseIDLabel);
        updateLabelPosition(messageLabel);

        //Styling
        okayButton.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());
    }

    // Method ot help center our labels
    public void updateLabelPosition(Label lbl){
        float width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(lbl.getText(), lbl.getFont());
        lbl.setLayoutX(150-width/2);
    }

}
