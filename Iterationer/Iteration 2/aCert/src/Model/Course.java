package Model;

import javafx.scene.control.Button;

/**
 * Created by dennis on 5/7/16.
 */
public class Course
{
    private String courseName;
    private String courseResponsible;
    private String startDate;
    private String endDate;

    public Course(String courseName, String courseResponsible, String startDate, String endDate)
    {
        this.courseName = courseName;
        this.courseResponsible = courseResponsible;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public String getCourseResponsible()
    {
        return courseResponsible;
    }

    public void setCourseResponsible(String courseResponsible)
    {
        this.courseResponsible = courseResponsible;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }
}
