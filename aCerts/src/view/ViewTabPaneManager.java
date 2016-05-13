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
        Tab mainView = new Tab("Main View");
        mainView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        mainView.setContent(new ViewPaneMain());

        Tab coursesView = new Tab("Courses");
        coursesView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        coursesView.setContent(new ViewPaneCourses());

        Tab courseParticipantsView = new Tab("CourseParticipants");
        courseParticipantsView.setOnSelectionChanged(e-> ((Resizable) tabViews.getSelectionModel().getSelectedItem().getContent()).updateLayout());
        courseParticipantsView.setContent(new ViewPaneCourseParticipants());

        tabViews = new TabPane(mainView, coursesView, courseParticipantsView);

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
