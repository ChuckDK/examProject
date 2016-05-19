package model.coursedata;

import javafx.scene.control.Button;

import java.util.Calendar;

/**
 * Created by dennis on 5/13/16.
 */
public class Course {
    private String courseName;
    private String courseResponsible;
    private Calendar startDate;
    private Calendar endDate;
    private Button downloadButton;
    private Button viewParticipants;

    public Course(String courseName, String courseResponsible, Calendar startDate, Calendar endDate, Button downloadButton, Button viewParticipants) {
        this.courseName = courseName;
        this.courseResponsible = courseResponsible;
        this.startDate = startDate;
        this.endDate = endDate;
        this.downloadButton = downloadButton;
        this.viewParticipants = viewParticipants;
    }

    public String getCourseName() {
        return courseName;
    }

    public String toString()
    {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseResponsible() {
        return courseResponsible;
    }

    public void setCourseResponsible(String courseResponsible) {
        this.courseResponsible = courseResponsible;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public Button getDownloadButton() {
        return downloadButton;
    }

    public void setDownloadButton(Button downloadButton) {
        this.downloadButton = downloadButton;
    }

    public Button getViewParticipants() {
        return viewParticipants;
    }

    public void setViewParticipants(Button viewParticipants) {
        this.viewParticipants = viewParticipants;
    }
}
