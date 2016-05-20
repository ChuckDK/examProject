package view.courses;

import javafx.scene.control.Button;
import view.styling.ACertsColorScheme;

public class ViewPaneCoursesAdmin extends ViewPaneCourses{

    private Button removeCourses;

    public ViewPaneCoursesAdmin()
    {
        //Call the superclass constructor so all the elements from that class can be added upon.
        super();

        //Initialize a remove button and set it's position and size.
        removeCourses = new Button("Remove");
        removeCourses.setLayoutX(10);
        removeCourses.setPrefWidth(80);

        //Add elements to the instance of the class.
        this.getChildren().addAll(removeCourses);

        //Styling the removeCourses button using ACertsColorScheme.
        removeCourses.setStyle(ACertsColorScheme.buttonColor());
    }

    //Override the updateLayout method from the Resizable interface to make
    //the ViewPaneCoursesAdmin appear as it's class dictates.
    @Override
    public void updateLayout()
    {
        //Sets the editor views size to be inside the current window with a little spacing added. 310 is the size of
        //the editor's side menu (300) plus a margin of 10.
        courseTableView.setPrefWidth(this.getScene().getWidth() - 110);

        //60 represents a margin of 10 on top and bottom and an estimated height of 40 of the tab pane
        courseTableView.setPrefHeight(this.getScene().getHeight() - 60);

        //Set the addNewCourses button to be above the removeButton button. 60 represents a margin of
        //10 from the bottom and between the addCourse button and removeCourseButton and an
        //estimated height of 40 of the tab pane
        addNewCourses.setLayoutY(this.getScene().getHeight() - addNewCourses.getHeight() - removeCourses.getHeight() - 60);


        //set the removeCourse button 10 pixels below the add courseButton
        removeCourses.setLayoutY(this.getScene().getHeight() - addNewCourses.getHeight() - 50);
    }
}

