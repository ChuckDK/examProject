import java.io.File;
import java.util.List;

public class Course {
    private int courseID;
    private String name, comments, description;
    private List<File> courseMaterial;

    public Course(int courseID, String name, String comments, String description) {
        this.courseID = courseID;
        this.name = name;
        this.comments = comments;
        this.description = description;
    }

    public void addCourseMaterial(File courseMaterial) {
        this.courseMaterial.add(courseMaterial);
    }

    public void addCourseMaterial(File... courseMaterial) {
        for (File file : courseMaterial) {
            this.courseMaterial.add(file);
        }
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<File> getCourseMaterial() {
        return courseMaterial;
    }

    public void setCourseMaterial(List<File> courseMaterial) {
        this.courseMaterial = courseMaterial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;

        Course course = (Course) o;

        return getCourseID() == course.getCourseID();

    }

    @Override
    public int hashCode() {
        return getCourseID();
    }
}
