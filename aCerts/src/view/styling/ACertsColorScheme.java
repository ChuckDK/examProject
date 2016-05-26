package view.styling;

//this class serves a a collection of default colors for menus and buttons. This way the color of all menus of one type
//can be changed by editing the color value here
public class ACertsColorScheme {

    public static String viewColor()
    {
        return "-fx-background-color: #A3CF5F";
    }

    public static String subViewColor()
    {
        return "-fx-background-color: #FFFFFF";
    }

    public static String buttonColor() { return  "-fx-base: #416B7F"; }

    public static String toggleButtonColor() { return "-fx-base: #416B7F"; }

    public static String tabColor() { return "-fx-base: #416B7F"; }

    public static String textFieldColor() { return "-fx-control-inner-background: #FFFFFF"; }
}
