package view.managers;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import view.courses.ViewPaneCourseParticipants;
import view.courses.ViewPaneCoursesAdmin;
import view.courseresponsibles.ViewPaneCourseResponsiblesAdmin;
import view.mainview.ViewPaneMain;
import view.settings.ViewPaneSettingsAdmin;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewTabPaneManagerAdmin extends Pane implements Resizable
{
    private TabPane tabViews;

    public ViewTabPaneManagerAdmin()
    {
        //initialize tabs
        Tab mainView = new Tab("Main View");
        Tab coursesView = new Tab("Courses");
        Tab courseParticipantsView = new Tab("Course Participants");
        Tab courseResponsiblesView = new Tab("Course Responsibles");
        Tab settingsView = new Tab("Settings");

        //add tabs
        tabViews = new TabPane(mainView, coursesView, courseParticipantsView, courseResponsiblesView, settingsView);

        //set the updatelayout method to be called when tab is selected
        mainView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        coursesView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        courseParticipantsView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        courseResponsiblesView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        settingsView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());

        //set the content of the tabs
        mainView.setContent(new ViewPaneMain());
        coursesView.setContent(new ViewPaneCoursesAdmin());
        courseParticipantsView.setContent(new ViewPaneCourseParticipants());
        courseResponsiblesView.setContent(new ViewPaneCourseResponsiblesAdmin());
        settingsView.setContent(new ViewPaneSettingsAdmin());

        //disable close button on tabs
        mainView.setClosable(false);
        coursesView.setClosable(false);
        courseParticipantsView.setClosable(false);
        courseResponsiblesView.setClosable(false);
        settingsView.setClosable(false);

        this.getChildren().add(tabViews);

        //styling
        mainView.setStyle(ACertsColorScheme.tabColor());
        coursesView.setStyle(ACertsColorScheme.tabColor());
        courseParticipantsView.setStyle(ACertsColorScheme.tabColor());
        courseResponsiblesView.setStyle(ACertsColorScheme.tabColor());
        settingsView.setStyle(ACertsColorScheme.tabColor());
    }

    @Override
    public void updateLayout()
    {
        double width = this.getScene().getWidth();
        double height = this.getScene().getHeight();
        tabViews.setPrefWidth(width);
        tabViews.setPrefHeight(height);

        ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout();
    }
}
