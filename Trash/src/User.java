public class User {
    private String firstName, lastName;
    private String eMail;
    private int userID;

    public User(String firstName, String lastName, String eMail, int userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.userID = userID;
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

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getUserID() == user.getUserID();

    }

    /**
     * Hashcode is based on the user ID since this should be unique
     * @return unique user ID
     */
    @Override
    public int hashCode() {
        return getUserID();
    }
}
