package view;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpFTPSettingsAdmin extends Pane implements Resizable {
    private TextField userNameTextField;
    private TextField passwordTextField;
    private TextField hostTextField;
    private TextField portTextField;

    private Button applyChangesButton;
    private Button cancelButton;

    public PopUpFTPSettingsAdmin() {
    }

    @Override
    public void updateLayout() {

    }
}
