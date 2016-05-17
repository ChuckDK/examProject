package view;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewTabPaneManager extends Pane implements Resizable {
    private TabPane tabViews;
    public ViewTabPaneManager()
    {
        //initialize tabs
        Tab mainView = new Tab("Main View");
        Tab coursesView = new Tab("Courses");
        Tab courseParticipantsView = new Tab("CourseParticipants");

        tabViews = new TabPane(mainView, coursesView, courseParticipantsView);

        //call the update method on the content of the tab when the tab is selected
        mainView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        coursesView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        courseParticipantsView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());

        //initialize panes in their corresponding tabs
        mainView.setContent(new ViewPaneMain());
        coursesView.setContent(new ViewPaneCourses());
        courseParticipantsView.setContent(new ViewPaneCourseParticipants());

        //disable the close features on the tabs
        mainView.setClosable(false);
        coursesView.setClosable(false);
        courseParticipantsView.setClosable(false);


        this.getChildren().add(tabViews);
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
