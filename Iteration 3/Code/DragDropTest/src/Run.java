import GUI.Certificate.CertificateEditor;
import GUI.Interfaces.Resizable;
import GUI.TablePanes.CoursesView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Run extends Application
{
    private Stage primaryStage;

    public static void main(String[] args)
    {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //initialize window
        this.primaryStage = primaryStage;
        this.primaryStage.show();

        //set startup scene to login view
        Scene scene = new Scene(new CertificateEditor(), 640, 480);
        this.primaryStage.setScene(scene);

        //add listener to update position of objects when window is resized
        this.primaryStage.getScene().widthProperty().addListener(e-> updateView());
        this.primaryStage.getScene().heightProperty().addListener(e-> updateView());
    }


    /*calls the updateLayout method on the current view, which repositions the elements on that view according to the
    window size*/

    public void updateView()
    {
        //check if the current view implements Resizeable, if it does then calls its UpdateLayout method
        if(this.primaryStage.getScene().getRoot() instanceof Resizable)
        {
            ((Resizable) this.primaryStage.getScene().getRoot()).updateLayout();
        }
    }
}
