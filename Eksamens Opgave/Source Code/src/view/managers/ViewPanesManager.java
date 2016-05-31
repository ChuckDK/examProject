package view.managers;

import javafx.scene.layout.Pane;
import view.courses.ViewPaneCourseParticipants;
import view.courses.ViewPaneCourses;
import view.courses.ViewPaneCoursesAdmin;
import view.courseresponsibles.ViewPaneCourseResponsiblesAdmin;
import view.mainview.ViewPaneMain;
import view.settings.ViewPaneSettingsAdmin;

import java.util.ArrayList;

//Class created after the singleton pattern
public class ViewPanesManager {
    private ArrayList<Pane> panes;
    private static ViewPanesManager viewPanesManager;

    //Constructor which checks whether the user has admin rights or has regular course responsible rights,
    //and then launches the program with a different set of panes depending on what type of rights the user has.
    //The constructor is private as it is required to create the singleton pattern effect.
    private ViewPanesManager(boolean isAdmin)
    {
        panes = new ArrayList<>();



        if(isAdmin)
        {
            panes.add(new ViewTabPaneManagerAdmin());
            panes.add(new ViewPaneCourseResponsiblesAdmin());
            panes.add(new ViewPaneCoursesAdmin());
            panes.add(new ViewPaneSettingsAdmin());
        }
        else
        {
            panes.add(new ViewTabPaneManager());
            panes.add(new ViewPaneCourses());
        }

        panes.add(new ViewPaneMain());
        panes.add(new ViewPaneCourseParticipants());
    }

    //getInstance method which is part of the singleton pattern.
    //It checks if an instance of a class has been created. If it hasn't it is created,
    //if it has the same instance is returned.
    //The only way to get an instance of the class is through this method as the constructor is private.
    public static ViewPanesManager getInstance(boolean isAdmin)
    {
        if(viewPanesManager == null)
        {
            viewPanesManager = new ViewPanesManager(isAdmin);
        }
        return viewPanesManager;
    }

    public Pane getPane(int index)
    {
        return panes.get(index);
    }
}
