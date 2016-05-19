package view.courses;

import javafx.scene.control.Button;
import view.styling.ACertsColorScheme;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneCoursesAdmin extends ViewPaneCourses{

    private Button removeCourses;

    public ViewPaneCoursesAdmin()
    {
        //call the superclass constructor so hat all the elements from that class is there to add upon
        super();

        //initialize remove button and set its position and size
        removeCourses = new Button("Remove");
        removeCourses.setLayoutX(10);
        removeCourses.setPrefWidth(80);

        //add elements to the pane
        this.getChildren().addAll(removeCourses);

        removeCourses.setStyle(ACertsColorScheme.buttonColor());
    }

    @Override
    public void updateLayout()
    {
        //sets the editorviews size to be inside the current window with a little spacing added. 310 is the size of
        //the editor sidemenu (300) plus a margin of 10.
        courseTableView.setPrefWidth(this.getScene().getWidth() - 110);

        //60 represents a margin of 10 on top and bottom and an estimated height of 40 of the tabpane
        courseTableView.setPrefHeight(this.getScene().getHeight() - 60);

        //set the addNewCourses button to be above the removeButton. 60 represents a margin of
        // 10 from the bottom and between the addcourse button and removecoursebutton and an
        // estimated height of 40 of the tabpane
        addNewCourses.setLayoutY(this.getScene().getHeight() - addNewCourses.getHeight() - removeCourses.getHeight() - 60);


        //set the removeCourse button 10 pixels below the add courseButton
        removeCourses.setLayoutY(this.getScene().getHeight() - addNewCourses.getHeight() - 50);
    }
}

