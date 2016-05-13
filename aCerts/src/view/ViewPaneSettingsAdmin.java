package view;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import model.CertificateTemplate;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneSettingsAdmin extends Pane implements Resizable{
    private Button removeCertificateButton;
    private Button addCourseCertificateTemplateButton;
    private Button changeFTPButton;
    private Button changeSMTPButton;
    private Button changeMYSQLButton;
    private ComboBox<CertificateTemplate> chooseCertificateComboBox;

    public ViewPaneSettingsAdmin()
    {

    }

    @Override
    public void updateLayout() {

    }
}
