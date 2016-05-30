import com.sun.javafx.geom.Shape;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class Ball extends Thread {

    private double x, y, deltaX, deltaY, radius;
    private Circle ball;
    private Scene scene;
    private String debug;
    private int counter;

    public Ball(double x, double y, double deltaX, double deltaY, double radius, Color color, Scene scene, String debug, int counter) {
        this.ball = new Circle(x, y, radius, color);
        this.x = x;
        this.y = y;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.scene = scene;
        this.radius = radius;
        this.debug = debug;
        this.counter = counter;
    }


    public void moveBall() {

        double minX, minY, maxX, maxY;

        minX = minY = 0.0;

        maxX = scene.getWidth() - radius;
        maxY = scene.getHeight() - radius;


        // Change the direction of the ball
        if (this.x < minX || this.x >= maxX)
            this.deltaX *= -1;
        if (this.y < minY || this.y >= maxY)
            this.deltaY *= -1;

        this.x += this.deltaX;
        this.y += this.deltaY;

        // Repaint the ball with the new coordinates
        this.ball.relocate(this.x, this.y);
    }

    public Circle getBall() {
        return ball;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            for (int i = 0; i < 100; i++)
                try {
                    this.moveBall();
                    Thread.sleep(100);
                    //System.out.println(currentThread())
                    System.out.println(this.debug);
                    System.out.println(this.counter++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //System.out.print(this.ball);
                    System.exit(1);
                }
        }
    }
}
