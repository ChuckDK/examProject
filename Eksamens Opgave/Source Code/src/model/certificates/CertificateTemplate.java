package model.certificates;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.Serializable;
import java.util.Calendar;

//Class from which enables initializing of CertificateTemplate objects. The class holds some getters and setters which
//may or may not be in use. The ones not in use are for later convenience.
public class CertificateTemplate implements Serializable{

    private int certificateID;

    private double participantNamePositionX;
    private double participantNamePositionY;

    private double courseNamePositionX;
    private double courseNamePositionY;

    private double datePositionX;
    private double datePositionY;

    private double nameFontSize;
    private double dateFontSize;
    private double courseFontSize;

    private String filename;

    public CertificateTemplate(int certificateID, double participantNamePositionX, double participantNamePositionY, double courseNamePositionX, double courseNamePositionY, double datePositionX, double datePositionY, double nameFontSize, double dateFontSize, double courseFontSize, String filename)
    {
        this.certificateID = certificateID;
        this.participantNamePositionX = participantNamePositionX;
        this.participantNamePositionY = participantNamePositionY;
        this.courseNamePositionX = courseNamePositionX;
        this.courseNamePositionY = courseNamePositionY;
        this.datePositionX = datePositionX;
        this.datePositionY = datePositionY;
        this.nameFontSize = nameFontSize;
        this.dateFontSize = dateFontSize;
        this.courseFontSize = courseFontSize;
        this.filename = filename;
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

    public double getCourseNamePositionX()
    {
        return courseNamePositionX;
    }

    public void setCourseNamePositionX(double courseNamePositionX)
    {
        this.courseNamePositionX = courseNamePositionX;
    }

    public double getCourseNamePositionY()
    {
        return courseNamePositionY;
    }

    public void setCourseNamePositionY(double courseNamePositionY)
    {
        this.courseNamePositionY = courseNamePositionY;
    }

    public double getDatePositionX()
    {
        return datePositionX;
    }

    public void setDatePositionX(double datePositionX)
    {
        this.datePositionX = datePositionX;
    }

    public double getDatePositionY()
    {
        return datePositionY;
    }

    public void setDatePositionY(double datePositionY)
    {
        this.datePositionY = datePositionY;
    }

    public double getNameFontSize()
    {
        return nameFontSize;
    }

    public void setNameFontSize(double nameFontSize)
    {
        this.nameFontSize = nameFontSize;
    }

    public double getDateFontSize()
    {
        return dateFontSize;
    }

    public void setDateFontSize(double dateFontSize)
    {
        this.dateFontSize = dateFontSize;
    }

    public double getCourseFontSize()
    {
        return courseFontSize;
    }

    public void setCourseFontSize(double courseFontSize)
    {
        this.courseFontSize = courseFontSize;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public int getCertificateID()
    {
        return certificateID;
    }

    public void setCertificateID(int certificateID)
    {
        this.certificateID = certificateID;
    }
}
