package view;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpAddCourseResponsible extends PopUpAddCoursePersona {

    private CheckBox adminRights;
    private Label adminRightsLabel;
    private Button addCourseResponsible;

    public PopUpAddCourseResponsible()
    {
        super();

        adminRights = new CheckBox();

        adminRightsLabel = new Label("Admin rights");

        addCourseResponsible = new Button("Add course responsible");

        //layouting
        adminRights.setLayoutX(320);
        adminRights.setLayoutY(210);

        adminRightsLabel.setLayoutX(350);
        adminRightsLabel.setLayoutY(210);

        addCourseResponsible.setLayoutX(30);
        addCourseResponsible.setLayoutY(350);
        //Add elements to view
        this.getChildren().addAll(adminRights, adminRightsLabel, addCourseResponsible);
    }
}
