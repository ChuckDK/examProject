package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
        //initialize removeCertificateTemplate Button and set its position on the pane and its width
        removeCertificateButton = new Button("Remove Certificate Template");
        removeCertificateButton.setLayoutX(10);
        removeCertificateButton.setLayoutY(10);
        removeCertificateButton.setPrefWidth(250);

        //initialize certificate template chooser combobox and set its position on the pane and its width
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


        //styling
        this.setStyle(ACertsColorScheme.viewColor());

        removeCertificateButton.setStyle(ACertsColorScheme.buttonBcolor());
        addCourseCertificateTemplateButton.setStyle(ACertsColorScheme.buttonBcolor());
        changeMYSQLButton.setStyle(ACertsColorScheme.buttonBcolor());
        changeFTPButton.setStyle(ACertsColorScheme.buttonBcolor());
        changeSMTPButton.setStyle(ACertsColorScheme.buttonBcolor());
        chooseCertificateComboBox.setStyle(ACertsColorScheme.buttonBcolor());

        //functionality
        addCourseCertificateTemplateButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Certificate Template Editor");
            PopUpCourseCertificateTemplateGeneratorAdmin popupPane = new PopUpCourseCertificateTemplateGeneratorAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));
            popup.getScene().widthProperty().addListener(ex-> SceneInitializer.updateView(popup.getScene()));
            popup.getScene().heightProperty().addListener(ex-> SceneInitializer.updateView(popup.getScene()));
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            ((Resizable) popup.getScene().getRoot()).updateLayout();

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });

        changeMYSQLButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("MySQL Settings");
            PopUpMySQLSettingsAdmin popupPane = new PopUpMySQLSettingsAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));
            //((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });

        changeSMTPButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("SMTP Settings");
            PopUpSMTPSettingsAdmin popupPane = new PopUpSMTPSettingsAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });
        changeFTPButton.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("FTP Settings");
            PopUpFTPSettingsAdmin popupPane = new PopUpFTPSettingsAdmin();
            popup.setScene(new Scene(popupPane,  500, 400));
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            popup.initModality(Modality.APPLICATION_MODAL);
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
