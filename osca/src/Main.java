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

        Ball ball1 = new Ball(250, 250, 10, 0.0, 25, Color.AQUA, scene,"Aqua", 0);
        Ball ball2 = new Ball(250, 250, 10, 10, 10, Color.CHOCOLATE, scene, "Chocolate", 0);
        Ball ball3 = new Ball(250, 250, 0, 10, 5, Color.FIREBRICK, scene, "Firebrick", 0);
        //Ball ball4 = new Ball(250, 250, -10, -10, 35, Color.BLACK, scene,"              Black");
        //Ball ball5 = new Ball(250, 250, -10, -10, 55, Color.GREENYELLOW, scene,"                GreenYellow");
        //Ball ball6 = new Ball(250, 250, -10, -10, 15, Color.GAINSBORO, scene,"                      Grainsboro");

        root.getChildren().addAll(ball1.getBall(), ball2.getBall(), ball3.getBall());
                //ball4.getBall(), ball5.getBall(), ball6.getBall());
        primaryStage.setScene(scene);
        primaryStage.show();
        ball1.start();
        ball2.start();
        ball3.start();
        //ball4.start();
        //ball5.start();
        //ball6.start();


    }
}
