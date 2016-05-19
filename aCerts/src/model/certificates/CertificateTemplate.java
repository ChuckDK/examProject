package model.certificates;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Calendar;

/**
 * Created by dennis on 5/13/16.
 */
public class CertificateTemplate {
    private double participantNamePositionX;
    private double participantNamePositionY;

    private double courseNamePositionX;
    private double courseNamePositionY;

    private double datePositionX;
    private double datePositionY;

    private double nameFontSize;
    private double dateFontSize;
    private double courseFontSize;

    private Color participantNameColor;
    private Color dateColor;
    private Color courseColor;

    private Font font;

    private Image certificateImage;

    public CertificateTemplate(double participantNamePositionX,
                               double participantNamePositionY,
                               double courseNamePositionX,
                               double courseNamePositionY,
                               double datePositionX,
                               double datePositionY,
                               double nameFontSize,
                               double dateFontSize,
                               double courseFontSize,
                               Color participantNameColor,
                               Color dateColor,
                               Color courseColor,
                               Font font,
                               Image certificateImage) {
        this.participantNamePositionX = participantNamePositionX;
        this.participantNamePositionY = participantNamePositionY;
        this.courseNamePositionX = courseNamePositionX;
        this.courseNamePositionY = courseNamePositionY;
        this.datePositionX = datePositionX;
        this.datePositionY = datePositionY;
        this.nameFontSize = nameFontSize;
        this.dateFontSize = dateFontSize;
        this.courseFontSize = courseFontSize;
        this.participantNameColor = participantNameColor;
        this.dateColor = dateColor;
        this.courseColor = courseColor;
        this.font = font;
        this.certificateImage = certificateImage;
    }

    public double getParticipantNamePositionX() {
        return participantNamePositionX;
    }

    public void setParticipantNamePositionX(double participantNamePositionX) {
        this.participantNamePositionX = participantNamePositionX;
    }

    public double getParticipantNamePositionY() {
        return participantNamePositionY;
    }

    public void setParticipantNamePositionY(double participantNamePositionY) {
        this.participantNamePositionY = participantNamePositionY;
    }

    public double getCourseNamePositionX() {
        return courseNamePositionX;
    }

    public void setCourseNamePositionX(double courseNamePositionX) {
        this.courseNamePositionX = courseNamePositionX;
    }

    public double getCourseNamePositionY() {
        return courseNamePositionY;
    }

    public void setCourseNamePositionY(double courseNamePositionY) {
        this.courseNamePositionY = courseNamePositionY;
    }

    public double getDatePositionX() {
        return datePositionX;
    }

    public void setDatePositionX(double datePositionX) {
        this.datePositionX = datePositionX;
    }

    public double getDatePositionY() {
        return datePositionY;
    }

    public void setDatePositionY(double datePositionY) {
        this.datePositionY = datePositionY;
    }

    public double getNameFontSize() {
        return nameFontSize;
    }

    public void setNameFontSize(double nameFontSize) {
        this.nameFontSize = nameFontSize;
    }

    public double getDateFontSize() {
        return dateFontSize;
    }

    public void setDateFontSize(double dateFontSize) {
        this.dateFontSize = dateFontSize;
    }

    public Color getParticipantNameColor() {
        return participantNameColor;
    }

    public void setParticipantNameColor(Color participantNameColor) {
        this.participantNameColor = participantNameColor;
    }

    public Color getDateColor() {
        return dateColor;
    }

    public void setDateColor(Color dateColor) {
        this.dateColor = dateColor;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Image getCertificateImage() {
        return certificateImage;
    }

    public void setCertificateImage(Image certificateImage) {
        this.certificateImage = certificateImage;
    }

    public double getCourseFontSize()
    {
        return courseFontSize;
    }

    public void setCourseFontSize(double courseFontSize)
    {
        this.courseFontSize = courseFontSize;
    }
}
