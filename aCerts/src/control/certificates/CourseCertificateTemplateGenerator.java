package control.certificates;

import control.operations.FTPOperations;
import control.settings.FTPSettings;
import control.settings.FileIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.certificates.CertificateTemplate;

import model.coursedata.CourseParticipant;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Calendar;

public class CourseCertificateTemplateGenerator
{
    public static boolean uploadCertificateTemplate(CertificateTemplate certificateTemplate, String templateName, File file)
    {
        try
        {
            FileIO.writeObject(Paths.get("").toAbsolutePath().toString()+"/templates/"+templateName, certificateTemplate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        FTPOperations.uploadFile(file);
        try
        {
            FTPOperations.uploadFile(new File(Paths.get("").toAbsolutePath().toString()+"/templates/"+templateName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean generateCertificateImage(CertificateTemplate template, File imageFile, CourseParticipant participant) throws Exception
    {
        Pane certificatePane = new Pane();

        //read and store the file as a BufferedImage object (BufferedImage is a swing class
        BufferedImage image = ImageIO.read(imageFile);

        //convert the BufferedImage to a javaFX image and show it in the editor, by setting the certificateImageViews
        // image to be the one converted from the BufferedImage
        Image loadedImage = SwingFXUtils.toFXImage(image, null);

        ImageView imageView = new ImageView(loadedImage);

        Calendar now = Calendar.getInstance();
        Label courseName = new Label(participant.getCourseName());
        Label participantName = new Label(participant.getFirstName()+" "+participant.getLastName());
        Label certificateDate = new Label(""+now.get(Calendar.DATE)+" "+now.get(Calendar.MONTH)+" "+now.get(Calendar.YEAR));

        courseName.setFont(Font.font(template.getCourseFontSize()));
        participantName.setFont(Font.font(template.getNameFontSize()));
        certificateDate.setFont(Font.font(template.getDateFontSize()));

        double width = 0;
        double height = 0;

        courseName.setLayoutX(template.getCourseNamePositionX() - width / 2);
        courseName.setLayoutY(template.getCourseNamePositionY() - height / 2);

        participantName.setLayoutX(template.getParticipantNamePositionX() - width / 2);
        participantName.setLayoutY(template.getParticipantNamePositionY() - height / 2);

        certificateDate.setLayoutX(template.getCourseNamePositionX() - width / 2);
        certificateDate.setLayoutY(template.getCourseNamePositionY() - height / 2);

        certificatePane.getChildren().addAll(
                imageView,
                courseName,
                participantName,
                certificateDate
        );

        Stage window = new Stage();
        Scene scene = new Scene(certificatePane);
        window.setScene(scene);
        window.show();

        Image certificateFinal = certificatePane.snapshot(new SnapshotParameters(), null);

        imageView.setImage(certificateFinal);

        return true;

    }
}
