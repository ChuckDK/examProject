package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpSMTPSettingsAdmin extends Pane implements Resizable {


    private Button emailAddressButton;
    private Label titleLabel;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private TextField hostTextField;
    private TextField portTextField;
    private Button applyChangesButton;
    private Button cancelButton;

    public PopUpSMTPSettingsAdmin() {
    }

    @Override
    public void updateLayout() {

    }
}
