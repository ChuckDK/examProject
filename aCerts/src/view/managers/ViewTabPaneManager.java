package view.managers;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import view.courses.ViewPaneCourseParticipants;
import view.courses.ViewPaneCourses;
import view.mainview.ViewPaneMain;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

public class ViewTabPaneManager extends Pane implements Resizable
{
    private TabPane tabViews;
    public ViewTabPaneManager()
    {
        //initialize tabs
        Tab mainView = new Tab("Main View");
        Tab coursesView = new Tab("Courses");
        Tab courseParticipantsView = new Tab("CourseParticipants");

        //add tabs to the TabPane called 'tabViews'.
        tabViews = new TabPane(mainView, coursesView, courseParticipantsView);

        //set the updateLayout method to be called when a tab is selected.
        mainView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        coursesView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        courseParticipantsView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());

        //Set the content of the tabs.
        //The content is essentially an instance of the class related to each specific pane.
        mainView.setContent(new ViewPaneMain());
        coursesView.setContent(new ViewPaneCourses());
        courseParticipantsView.setContent(new ViewPaneCourseParticipants());

        //disable the default close feature on the tabs
        mainView.setClosable(false);
        coursesView.setClosable(false);
        courseParticipantsView.setClosable(false);

        //Add 'tabViews' to an instance of this class.
        this.getChildren().add(tabViews);

        //Styling the tabs with ACertsColorScheme.
        mainView.setStyle(ACertsColorScheme.tabColor());
        coursesView.setStyle(ACertsColorScheme.tabColor());
        courseParticipantsView.setStyle(ACertsColorScheme.tabColor());
    }

    //Override the updateLayout method from the Resizable interface to make
    //the ViewTabPaneManager appear as it's class dictates.
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
