package view.start;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.styling.Resizable;
import view.login.ViewPaneLogin;

//Class which runs an instance of other classes
//as all the windows of the program essentially are created by instances of their respective classes.
public class SceneInitializer {

    private static Stage mainWindow;

    //Static method which creates a resizable window.
    public static void initialize(Stage window)
    {
        mainWindow = window;
        Scene scene = new Scene(new ViewPaneLogin(), 500, 400);
        window.show();
        window.setScene(scene);
        window.setTitle("AppAcademy Certificate Manager");

        window.getScene().widthProperty().addListener(e-> updateView(scene));
        window.getScene().heightProperty().addListener(e-> updateView(scene));
    }

    public static void updateView(Scene scene)
    {
        //check if the current view implements Resizeable, if it does then calls its UpdateLayout method
        if(scene.getRoot() instanceof Resizable)
        {
            ((Resizable) scene.getRoot()).updateLayout();
        }
    }

    public static Stage getMainWindow()
    {
        return mainWindow;
    }
}
