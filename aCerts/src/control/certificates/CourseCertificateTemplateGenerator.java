package control.certificates;

import control.operations.FTPOperations;
import control.operations.SMTPOperations;
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
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.util.Calendar;

public class CourseCertificateTemplateGenerator
{
    //Method which uploads a certificate template and the template image used to create the template to the FTP server.
    public static boolean uploadCertificateTemplate(CertificateTemplate certificateTemplate, String templateName, File file)
    {
        //Try to write a CertificateTemplate object to the templates folder.
        try
        {
            FileIO.writeObject(Paths.get("").toAbsolutePath().toString()+"/templates/"+templateName, certificateTemplate);
        }
        //Handle any exceptions.
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        //Try to upload file from scope via the uploadFile method.
        try
        {
            FTPOperations.uploadFile(file);
            FTPOperations.uploadFile(new File(Paths.get("").toAbsolutePath().toString()+"/templates/"+templateName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //Method which generate the image.
    public static String generateCertificateImage(CertificateTemplate template, File imageFile, CourseParticipant participant)
    {
        //Pane used as a work space to throw in all the things needed for creation of the image.
        Pane certificatePane = new Pane();

        //Read and store the file as a BufferedImage object (BufferedImage is a swing class).
        //It needs to be null, else wise we can't use the try-catch on it.
        BufferedImage image = null;

        //Try to read the buffered image.
        try
        {
            image = ImageIO.read(imageFile);
        }

        //Handle any IOExceptions.
        catch (IOException e)
        {
            e.printStackTrace();
        }

        //Convert the BufferedImage to a javaFX image.
        Image loadedImage = SwingFXUtils.toFXImage(image, null);

        //Display the newly created javafx image in an image view.
        ImageView imageView = new ImageView(loadedImage);

        //Initialize labels which will be present on the template.
        Calendar now = Calendar.getInstance();
        Label courseName = new Label(participant.getCourseName());
        Label participantName = new Label(participant.getFirstName()+" "+participant.getLastName());
        Label certificateDate = new Label(""+now.get(Calendar.DATE)+" "+now.get(Calendar.MONTH)+" "+now.get(Calendar.YEAR));

        //Set text size on the labels.
        courseName.setFont(Font.font(template.getCourseFontSize()));
        participantName.setFont(Font.font(template.getNameFontSize()));
        certificateDate.setFont(Font.font(template.getDateFontSize()));

        //Fue to javafx limitations, we need to compute the width of the course name label, using a workaround.
        //JavaFX does not update width and height instantly, therefore the workaround.
        double width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(courseName.getText(), courseName.getFont());
        double height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(courseName.getFont().getSize())).getLineHeight();

        //Set the position of the course name label.
        courseName.setLayoutX(template.getCourseNamePositionX() - width / 2);
        courseName.setLayoutY(template.getCourseNamePositionY() - height / 2);

        //Fue to javafx limitations, we need to compute the width of the participant name label, using a workaround.
        //JavaFX does not update width and height instantly, therefore the workaround.
        width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(participantName.getText(), participantName.getFont());
        height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(participantName.getFont().getSize())).getLineHeight();

        //Set the position of the participant name label.
        participantName.setLayoutX(template.getParticipantNamePositionX() - width / 2);
        participantName.setLayoutY(template.getParticipantNamePositionY() - height / 2);

        //Fue to javafx limitations, we need to compute the width of the certificate date label, using a workaround.
        //JavaFX does not update width and height instantly, therefore the workaround.
        width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(certificateDate.getText(), certificateDate.getFont());
        height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(certificateDate.getFont().getSize())).getLineHeight();

        //Set the position of the certificate date label.
        certificateDate.setLayoutX(template.getDatePositionX() - width / 2);
        certificateDate.setLayoutY(template.getDatePositionY() - height / 2);

        //Add all elements to the "workspace" pane.
        certificatePane.getChildren().addAll(
                imageView,
                courseName,
                participantName,
                certificateDate
        );

        //Initialize the "workspace" stage
        Stage window = new Stage();
        Scene scene = new Scene(certificatePane);
        window.setScene(scene);
        //If we don't use window.show() before this step, the stage elements will not be visible on the screenshot.
        //This is a JavaFX decision.
        window.show();

        //Take a screenshot of the newly created pane.
        Image certificateFinal = certificatePane.snapshot(new SnapshotParameters(), null);

        //Set the screenshot to be the image in the image view.
        imageView.setImage(certificateFinal);

        //Close the window.
        window.close();

        //Method to save javafx image as jpg image found at http://stackoverflow.com/questions/19548363/image-saved-in-javafx-as-jpg-is-pink-toned
        //from the best answer. 24-may-2016
        BufferedImage jpg = SwingFXUtils.fromFXImage(certificateFinal, null);

        BufferedImage jpgNoAlpha = new BufferedImage(jpg.getWidth(), jpg.getHeight(), BufferedImage.OPAQUE);

        Graphics2D jpgGraphics = jpgNoAlpha.createGraphics();

        jpgGraphics.drawImage(jpg, 0, 0, null);

        try
        {
            ImageIO.write(jpgNoAlpha, "jpg", new File("certificates/"+participant.getCourseName()+".jpg"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //end of jpg image saving

        //send the certificate to participant
        SMTPOperations.sendCertificate(participant.getEmail(), "Tillykke med dit certifikat", "Kursuscertifikat fra AppAcademy", new File("certificates/"+participant.getCourseName()+".jpg"));


        return null;

    }
}
