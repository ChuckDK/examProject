package Model;

import javafx.scene.control.Button;

/**
 * Created by dennis on 5/7/16.
 */
public class CourseParticipant
{
    private String courseName;
    private String firstName;
    private String lastName;
    private String email;
    private Button send;

    public CourseParticipant(String courseName, String firstName, String lastName, String email, Button send)
    {
        this.courseName = courseName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.send = send;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Button getSend()
    {
        return send;
    }

    public void setSend(Button send)
    {
        this.send = send;
    }
}
