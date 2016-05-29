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
        Scene scene = new Scene(root);

        Ball ball1 = new Ball(250, 250, 10, 0.0, 25, Color.AQUA, scene);
        Ball ball2 = new Ball(250, 250, 10, 10, 10, Color.CHOCOLATE, scene);
        Ball ball3 = new Ball(250, 250, 0, 10, 5, Color.FIREBRICK, scene);
        Ball ball4 = new Ball(250, 250, -10, -10, 35, Color.BLACK, scene);
        Ball ball5 = new Ball(250, 250, -10, -10, 55, Color.GREENYELLOW, scene);
        Ball ball6 = new Ball(250, 250, -10, -10, 15, Color.GAINSBORO, scene);

        root.getChildren().addAll(ball1.getBall(), ball2.getBall(), ball3.getBall(), ball4.getBall(), ball5.getBall(), ball6.getBall());
        primaryStage.setScene(scene);
        primaryStage.show();
        ball1.run();
        ball2.run();
        ball3.run();
        ball4.run();
        ball5.run();
        ball6.run();


    }
}
