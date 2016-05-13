package model;

import javafx.scene.control.Button;

/**
 * Created by dennis on 5/13/16.
 */
public class CourseParticipant {
    private String courseName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean certificateSent;
    private Button sendButton;

    public CourseParticipant(String courseName, String firstName, String lastName, String email, String phoneNumber,boolean certificateSent, Button sendButton) {
        this.courseName = courseName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sendButton = sendButton;
        this.certificateSent = certificateSent;
    }

    public String toString()
    {
        return firstName+" "+lastName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Button getSendButton() {
        return sendButton;
    }

    public void setSendButton(Button sendButton) {
        this.sendButton = sendButton;
    }

    public boolean isCertificateSent()
    {
        return certificateSent;
    }

    public void setCertificateSent(boolean certificateSent)
    {
        this.certificateSent = certificateSent;
    }
}
