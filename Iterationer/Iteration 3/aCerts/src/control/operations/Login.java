package control.operations;

public class Login {
    private static boolean isAdmin;

    public static boolean loginVerification(String username, String password)
    {
        return false;
    }

    public static boolean isAdmin()
    {
        return isAdmin;
    }

    private static void setAdmin(boolean admin)
    {
        isAdmin = admin;
    }
}
