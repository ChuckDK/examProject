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
            return false;
        }
        return true;
    }

    public static String generateCertificateImage(CertificateTemplate template, File imageFile, CourseParticipant participant)
    {
        Pane certificatePane = new Pane();

        //read and store the file as a BufferedImage object (BufferedImage is a swing class
        BufferedImage image = null;
        try
        {
            image = ImageIO.read(imageFile);
        } catch (IOException e)
        {
            e.printStackTrace();
        }

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

        double width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(courseName.getText(), courseName.getFont());
        double height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(courseName.getFont().getSize())).getLineHeight();

        courseName.setLayoutX(template.getCourseNamePositionX() - width / 2);
        courseName.setLayoutY(template.getCourseNamePositionY() - height / 2);

        width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(participantName.getText(), participantName.getFont());
        height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(participantName.getFont().getSize())).getLineHeight();

        participantName.setLayoutX(template.getParticipantNamePositionX() - width / 2);
        participantName.setLayoutY(template.getParticipantNamePositionY() - height / 2);

        width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(certificateDate.getText(), certificateDate.getFont());
        height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(certificateDate.getFont().getSize())).getLineHeight();

        certificateDate.setLayoutX(template.getDatePositionX() - width / 2);
        certificateDate.setLayoutY(template.getDatePositionY() - height / 2);

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

        window.close();

        //method to save javafx image as jpg image found at http://stackoverflow.com/questions/19548363/image-saved-in-javafx-as-jpg-is-pink-toned
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
