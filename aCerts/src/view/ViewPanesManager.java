package view;

import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPanesManager {
    private ArrayList<Pane> panes;

    private ViewPanesManager() {
    }

    public static ViewPanesManager getInstance()
    {
        return null;
    }

    public Pane getPane(int index)
    {
        return panes.get(index);
    }
}
