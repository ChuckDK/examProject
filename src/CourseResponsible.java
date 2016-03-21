import java.util.List;

public class CourseResponsible extends User {
    private List<Course> courses;
    private String password;
    private int phoneNumber;

    public CourseResponsible(String firstName, String lastName, String eMail, int userID, List<Course> courses, String password, int phoneNumber) {
        super(firstName, lastName, eMail, userID);
        this.courses = courses;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
