package view;

/**
 * Created by dennis on 5/13/16.
 */

//this class serves a a collection of default colors for menus and buttons. This way the color of all menus of one type
//can be changed by editing the color value here
public class ACertsColorScheme {

    public static String viewColor()
    {
        return "-fx-background-color: #CCFF99";
    }

    public static String subViewColor()
    {
        return "-fx-background-color: #FFFFFF";
    }

    public static String buttonColor() { return  "-fx-base: #CCDF99"; }

    public static String toggleButtonColor() { return "-fx-base: #EEFF99"; }

    public static String tabColor() { return "-fx-base: #EEFF99"; }

    public static String textFieldColor() { return "-fx-control-inner-background: #FFFFFF"; }
}
