package view.settings;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.certificates.CertificateTemplate;
import view.certificatetemplate.PopUpCourseCertificateTemplateGeneratorAdmin;
import view.start.SceneInitializer;
import view.styling.ACertsColorScheme;
import view.styling.Resizable;

/**
 * Created by dennis on 5/13/16.
 */
public class ViewPaneSettingsAdmin extends Pane implements Resizable
{
    private Button removeCertificateButton;
    private Button addCourseCertificateTemplateButton;
    private Button changeFTPButton;
    private Button changeSMTPButton;
    private Button changeMYSQLButton;
    private ComboBox<CertificateTemplate> chooseCertificateComboBox;

    public ViewPaneSettingsAdmin()
    {
        //initialize removeCertificateTemplate Button and set its position on the pane and its width
        removeCertificateButton = new Button("Remove Certificate Template");
        removeCertificateButton.setLayoutX(10);
        removeCertificateButton.setLayoutY(10);
        removeCertificateButton.setPrefWidth(250);

        //initialize certificate template chooser combo box and set its position on the pane and its width
        chooseCertificateComboBox = new ComboBox<>();
        chooseCertificateComboBox.setLayoutX(270);
        chooseCertificateComboBox.setLayoutY(10);
        chooseCertificateComboBox.setPrefWidth(200);

        //initialize addCertificateTemplate Button and set its position on the pane and its width
        addCourseCertificateTemplateButton = new Button("Add Certificate Template");
        addCourseCertificateTemplateButton.setLayoutX(10);
        addCourseCertificateTemplateButton.setLayoutY(40);
        addCourseCertificateTemplateButton.setPrefWidth(250);

        //initialize changeSMTP Button and set its position on the pane and its width
        changeSMTPButton = new Button("Change SMTP Settings");
        changeSMTPButton.setLayoutX(10);
        changeSMTPButton.setLayoutY(90);
        changeSMTPButton.setPrefWidth(250);

        //initialize changeFTP Button and set its position on the pane and its width
        changeFTPButton = new Button("Change FTP Settings");
        changeFTPButton.setLayoutX(10);
        changeFTPButton.setLayoutY(120);
        changeFTPButton.setPrefWidth(250);

        //initialize changeMYSQL Button and set its position on the pane and its width
        changeMYSQLButton = new Button("Change MySQL Settings");
        changeMYSQLButton.setLayoutX(10);
        changeMYSQLButton.setLayoutY(170);
        changeMYSQLButton.setPrefWidth(250);

        //add all elements to the pane
        this.getChildren().addAll(
                removeCertificateButton,
                addCourseCertificateTemplateButton,
                changeFTPButton,
                changeMYSQLButton,
                changeSMTPButton,
                chooseCertificateComboBox);


        //Styling the nodes with ACertsColorScheme.
        this.setStyle(ACertsColorScheme.viewColor());

        removeCertificateButton.setStyle(ACertsColorScheme.buttonColor());
        addCourseCertificateTemplateButton.setStyle(ACertsColorScheme.buttonColor());
        changeMYSQLButton.setStyle(ACertsColorScheme.buttonColor());
        changeFTPButton.setStyle(ACertsColorScheme.buttonColor());
        changeSMTPButton.setStyle(ACertsColorScheme.buttonColor());
        chooseCertificateComboBox.setStyle(ACertsColorScheme.buttonColor());

        //Adding a function for the addCourseCertificateTemplateButton button.
        addCourseCertificateTemplateButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Certificate Template Editor");
            PopUpCourseCertificateTemplateGeneratorAdmin popupPane = new PopUpCourseCertificateTemplateGeneratorAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));

            //Listeners which calls updateView whenever the window's width is resized.
            popup.getScene().widthProperty().addListener(ex-> SceneInitializer.updateView(popup.getScene()));

            //Listeners which calls updateView whenever the window's height is resized.
            popup.getScene().heightProperty().addListener(ex-> SceneInitializer.updateView(popup.getScene()));

            //Closing the course certificate template generator window.
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            //When the 'popup' scene is launched, it's elements are not in their appropriate places.
            //This is taken care of by calling the updateLayout method which places all the nodes correctly
            //dictated by the getRoot. However to call updateLayout, the popup must be type casted to (Resizable)
            ((Resizable) popup.getScene().getRoot()).updateLayout();

            //Blocks input events or user interaction, as long as this window isn't taken care of.
            popup.initModality(Modality.APPLICATION_MODAL);

            //Prevents the user from changing windows before this window has been closed. In conjunction with
            //the line "initModality(Modality.APPLICATION_MODAL);", it creates the "pop up window" effect.
            popup.showAndWait();
        });

        //Adding a function for the changeMYSQLButton button.
        changeMYSQLButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("MySQL Settings");
            PopUpMySQLSettingsAdmin popupPane = new PopUpMySQLSettingsAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));

            //Closing the MySQL settings popup.
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            //Blocks input events or user interaction, as long as this window isn't taken care of.
            popup.initModality(Modality.APPLICATION_MODAL);

            //Prevents the user from changing windows before this window has been closed. In conjunction with
            //the line "initModality(Modality.APPLICATION_MODAL);", it creates the "pop up window" effect.
            popup.showAndWait();
        });

        //Adding a function for the changeSMTPButton button.
        changeSMTPButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("SMTP Settings");
            PopUpSMTPSettingsAdmin popupPane = new PopUpSMTPSettingsAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));

            //Closing the SMTPSettings popup
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            //Blocks input events or user interaction, as long as this window isn't taken care of.
            popup.initModality(Modality.APPLICATION_MODAL);

            //Prevents the user from changing windows before this window has been closed. In conjunction with
            //the line "initModality(Modality.APPLICATION_MODAL);", it creates the "pop up window" effect.
            popup.showAndWait();
        });

        //Adding a function for the changeFTPButton button.
        changeFTPButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("FTP Settings");
            PopUpFTPSettingsAdmin popupPane = new PopUpFTPSettingsAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));

            //Closing the SMTPSettings popup.
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            //Blocks input events or user interaction, as long as this window isn't taken care of.
            popup.initModality(Modality.APPLICATION_MODAL);

            //Prevents the user from changing windows before this window has been closed. In conjunction with
            //the line "initModality(Modality.APPLICATION_MODAL);", it creates the "pop up window" effect.
            popup.showAndWait();
        });
    }

    /**this class does not need to reposition elements since none of them are dependant on the window size. Therefore
     * the updateLayout method body is empty
     */
    @Override
    public void updateLayout() {

    }
}
