package view;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPanesManager {
    private ArrayList<Pane> panes;
    private static ViewPanesManager viewPanesManager;

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
