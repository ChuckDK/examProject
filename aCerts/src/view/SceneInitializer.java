package view;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by dennis on 5/13/16.
 */
public class SceneInitializer {

    private static Stage mainWindow;
    public static void initialize(Stage window)
    {
        mainWindow = window;
        Scene scene = new Scene(new PopUpFTPSettingsAdmin(), 500, 400);
        window.show();
        window.setScene(scene);

        window.getScene().widthProperty().addListener(e-> updateView());
        window.getScene().heightProperty().addListener(e-> updateView());
    }

    public static void updateView()
    {
        //check if the current view implements Resizeable, if it does then calls its UpdateLayout method
        if(mainWindow.getScene().getRoot() instanceof Resizable)
        {
            ((Resizable) mainWindow.getScene().getRoot()).updateLayout();
        }
    }
}
