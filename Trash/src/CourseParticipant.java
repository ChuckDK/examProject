import java.util.ArrayList;
import java.util.List;

public class CourseParticipant extends User {
    private List<Course> courses;

    public CourseParticipant(String firstName, String lastName, String eMail, int userID) {
        super(firstName, lastName, eMail, userID);
        this.courses = new ArrayList<>();
    }
}
