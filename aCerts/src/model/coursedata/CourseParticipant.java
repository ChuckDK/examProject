package model.coursedata;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

//Class from which enables initializing of CourseParticipant objects. The class holds some getters and setters which
//may or may not be in use. The ones not in use are for later convenience.
public class CourseParticipant {
    private String courseName;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String phoneNumber2;
    private SimpleBooleanProperty certificateSent;
    private Button sendButton;

    public CourseParticipant(String courseName, String firstName, String lastName, String email, String phoneNumber, String phoneNumber2, SimpleBooleanProperty certificateSent, Button sendButton)
    {
        this.courseName = courseName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.phoneNumber2 = phoneNumber2;
        this.certificateSent = certificateSent;
        this.sendButton = sendButton;
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

    public boolean getCertificateSent()
    {
        return certificateSent.get();
    }

    public SimpleBooleanProperty certificateSentProperty()
    {
        return certificateSent;
    }

    public void setCertificateSent(boolean certificateSent)
    {
        this.certificateSent.set(certificateSent);
    }

    public String getPhoneNumber2() {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        this.phoneNumber2 = phoneNumber2;
    }
}
