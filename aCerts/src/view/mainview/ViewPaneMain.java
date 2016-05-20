package view.mainview;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.coursedata.Course;
import model.coursedata.CourseParticipant;
import view.helpers.Utility;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

import java.util.Calendar;

//Class for the main window (the one the user lands on after login).
public class ViewPaneMain extends Pane implements Resizable
{
    private ListView<Course> activeCoursesList;
    private ListView<CourseParticipant> participantsWithNoCertificates;
    private Label activeCourseListLabel;
    private Label missingCertificatesLabel;

    public ViewPaneMain()
    {
        //Create a list view which can hold course objects.
        activeCoursesList = new ListView<Course>();

        //Create a dummy course
        Course dummyCourse = new Course("ads", "asda", Calendar.getInstance(), Calendar.getInstance(), new Button(), new Button());

        //Add the dummy course to the list view
        activeCoursesList.getItems().addAll(dummyCourse);

        //Style the instance of the class with ACertsColorScheme.
        this.setStyle(ACertsColorScheme.viewColor());



        participantsWithNoCertificates = new ListView<CourseParticipant>();
        //CourseParticipant dummypart = new CourseParticipant("","","","","", boolean,new Button());
        //participantsWithNoCertificates.getItems().addAll(dummypart);

        //Add some labels.
        activeCourseListLabel = new Label("Active Courses");

        missingCertificatesLabel = new Label("Missing certificates");

        //Add it all to the instance of the class.
        this.getChildren().addAll(
                activeCoursesList,
                participantsWithNoCertificates,
                activeCourseListLabel,
                missingCertificatesLabel
        );
    }

    //Override the updateLayout method from the Resizable interface to make
    //the ViewPaneMain appear as it's class dictates.
    @Override
    public void updateLayout()
    {
        double margin = 20;

        //Because this is a scene with a tab pane,
        //the getParentTabPane method from the Utility class must be called upon to find the size of the pane
        double width = Utility.getParentTabPane(this).getScene().getWidth();

        double height = Utility.getParentTabPane(this).getScene().getHeight();

        activeCoursesList.setPrefWidth(width  / 2 - margin * 2);
        activeCoursesList.setPrefHeight(height - margin * 5);

        activeCoursesList.setLayoutX(margin);
        activeCoursesList.setLayoutY(margin * 2);

        participantsWithNoCertificates.setPrefWidth(width / 2 - margin * 2);
        participantsWithNoCertificates.setPrefHeight(height - margin * 5);

        participantsWithNoCertificates.setLayoutX(width / 2 + margin);
        participantsWithNoCertificates.setLayoutY(margin * 2);

        activeCourseListLabel.setLayoutX(margin);
        activeCourseListLabel.setLayoutY(margin);

        missingCertificatesLabel.setLayoutX(width / 2 + margin);
        missingCertificatesLabel.setLayoutY(margin);


    }
}
