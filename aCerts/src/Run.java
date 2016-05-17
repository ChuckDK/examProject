import javafx.application.Application;
import javafx.stage.Stage;
import view.SceneInitializer;

import java.util.Locale;

/**
 * Created by dennis on 5/13/16.
 */
public class Run extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage window) throws Exception
    {
        SceneInitializer.initialize(window);
    }
}
