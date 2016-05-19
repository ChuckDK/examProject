package view.mainview;

import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import model.coursedata.Course;
import model.coursedata.CourseParticipant;
import view.helpers.Utility;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

import java.util.Calendar;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneMain extends Pane implements Resizable
{
    private ListView<Course> activeCoursesList;
    private ListView<CourseParticipant> participantsWithNoCertificates;
    private Label activeCourseListLabel;
    private Label missingCertificatesLabel;

    public ViewPaneMain()
    {
        activeCoursesList = new ListView<Course>();
        Course dummyCourse = new Course("ads", "asda", Calendar.getInstance(), Calendar.getInstance(), new Button(), new Button());
        activeCoursesList.getItems().addAll(dummyCourse);

        this.setStyle(ACertsColorScheme.viewColor());



        participantsWithNoCertificates = new ListView<CourseParticipant>();
        CourseParticipant dummypart = new CourseParticipant("","","","","", false,new Button());
        participantsWithNoCertificates.getItems().addAll(dummypart);

        activeCourseListLabel = new Label("Active Courses");

        missingCertificatesLabel = new Label("Missing certificates");

        this.getChildren().addAll(
                activeCoursesList,
                participantsWithNoCertificates,
                activeCourseListLabel,
                missingCertificatesLabel
        );
    }

    @Override
    public void updateLayout()
    {
        double margin = 20;

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
