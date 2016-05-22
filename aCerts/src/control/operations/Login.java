package control.operations;

public class Login {
    private static boolean isAdmin;
    private static String userEmail;

    public static boolean loginVerification(String username, String password)
    {
        return false;
    }

    public static boolean isAdmin()
    {
        return isAdmin;
    }

    public static void setAdmin(boolean admin)
    {
        isAdmin = admin;
    }

    public static String getUserEmail()
    {
        return userEmail;
    }

    public static void setUserEmail(String userEmail)
    {
        Login.userEmail = userEmail;
    }
}
