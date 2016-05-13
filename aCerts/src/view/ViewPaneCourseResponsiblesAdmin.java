package view;

import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import model.CourseResponsible;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneCourseResponsiblesAdmin extends Pane implements Resizable {

    private TableView<CourseResponsible> courseResponsiblesTableView;
    private ToggleButton activeCourseResponsiblesToggleButton;
    private ToggleButton inActiveCourseResponsiblesToggleButton;

    public ViewPaneCourseResponsiblesAdmin() {
    }

    @Override
    public void updateLayout() {

    }
}
