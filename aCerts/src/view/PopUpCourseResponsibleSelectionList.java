package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import model.CourseResponsible;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpCourseResponsibleSelectionList extends Pane {
    private TableView<CourseResponsible> courseResponsiblesTableView;
    private Label titleLabel;
    private Button selectCourseResponsibleButton;
    private Button cancelButton;

    public PopUpCourseResponsibleSelectionList() {
    }
}
