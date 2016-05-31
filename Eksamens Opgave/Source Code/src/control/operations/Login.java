package control.operations;

//Class which saves the mail used for login to use for default mail when a course is created.
public class Login {
    private static String userEmail;

    public static String getUserEmail()
    {
        return userEmail;
    }

    public static void setUserEmail(String userEmail)
    {
        Login.userEmail = userEmail;
    }
}
