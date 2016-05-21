import javafx.application.Application;
import javafx.stage.Stage;
import view.start.SceneInitializer;

public class Run extends Application {

    //Launching the program's first scene, essentially launching the program.
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage window) throws Exception
    {
        SceneInitializer.initialize(window);
    }
}
