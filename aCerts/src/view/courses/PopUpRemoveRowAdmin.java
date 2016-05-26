package view.courses;

import control.operations.SQLOperations;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.styling.ACertsColorScheme;

public class PopUpRemoveRowAdmin extends Pane {
    private TextField courseIDTextField;
    private Label instructionsLabel;
    private Button confirmButton;
    private Button cancelButton;

    public PopUpRemoveRowAdmin(String message, String promptText)
    {
        //Create the text field, the label, and the two buttons.
        cancelButton = new Button("Cancel");
        courseIDTextField = new TextField();
        instructionsLabel = new Label(message);
        confirmButton = new Button("Confirm");

        //Set the position and the prompt text for the text field.
        courseIDTextField.setLayoutX(100);
        courseIDTextField.setLayoutY(100);
        courseIDTextField.setPromptText(promptText);

        //Set the position for the instructionsLabel.
        //instructionsLabel.setLayoutX(20);
        //instructionsLabel.setLayoutY(50);

        //center message
        instructionsLabel.setTextAlignment(TextAlignment.CENTER);
        instructionsLabel.setAlignment(Pos.CENTER);

        //Set the position for the confirmButton.
        confirmButton.setLayoutX(80);
        confirmButton.setLayoutY(200);

        //Make an action on the button which creates a new instance of the PopUpRowRemovedAdmin.
        //This instance must be dealt with before any other windows can be used.


        //Set the position for the cancelButton.
        cancelButton.setLayoutX(150);
        cancelButton.setLayoutY(200);

        //Styling
        cancelButton.setStyle(ACertsColorScheme.buttonColor());
        confirmButton.setStyle(ACertsColorScheme.buttonColor());
        this.setStyle(ACertsColorScheme.viewColor());

        instructionsLabel.setStyle("-fx-text-fill: FF0000");


        //Add all the newly created nodes to the pane.
        this.getChildren().addAll(cancelButton, courseIDTextField, instructionsLabel, confirmButton);
    }
}
