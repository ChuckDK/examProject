package model.coursedata;

public class CourseResponsible {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String phoneNumber2;
    private String email;
    private boolean adminRights;

    public CourseResponsible(String firstName, String lastName, String phoneNumber, String phoneNumber2, String email, boolean adminRights)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.phoneNumber2 = phoneNumber2;
        this.email = email;
        this.adminRights = adminRights;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber2()
    {
        return phoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2)
    {
        this.phoneNumber2 = phoneNumber2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean hasAdminRights()
    {
        return adminRights;
    }
}
