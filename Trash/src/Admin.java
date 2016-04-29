public class Admin extends User {
    private String password;
    private int phoneNumber;

    public Admin(String firstName, String lastName, String eMail, int userID) {
        super(firstName, lastName, eMail, userID);
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
