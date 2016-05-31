import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bouncy Screen Saver");


        Group root = new Group();
        Scene scene = new Scene(root, 400, 400);

        Ball ball1 = new Ball(250, 250, 10, 0.0, 25, Color.AQUA, scene);
        Ball ball2 = new Ball(250, 250, 10, 10, 10, Color.CHOCOLATE, scene);
        Ball ball3 = new Ball(250, 250, 0, 10, 5, Color.FIREBRICK, scene);

        Thread thr1 = new Thread(ball1);
        Thread thr2 = new Thread(ball2);
        Thread thr3 = new Thread(ball3);
        // Kills the thread when main thread is closed ie closing the GUI window
        thr1.setDaemon(true);
        thr2.setDaemon(true);
        thr3.setDaemon(true);

        root.getChildren().addAll(ball1.getBall(), ball2.getBall(), ball3.getBall());
        primaryStage.setScene(scene);
        primaryStage.show();
        thr1.start();
        thr2.start();
        thr3.start();
    }
}
