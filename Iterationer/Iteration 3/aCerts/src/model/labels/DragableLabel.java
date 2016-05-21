package model.labels;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class DragableLabel extends ImageView
{
    private Label attachedLabel;
    private Double dragDeltaX;
    private Double dragDeltaY;

    public DragableLabel(String name)
    {
        //define a label that will be attached to this object.
        attachedLabel = new Label(name);
        //make mouse events not react on this label
        attachedLabel.setMouseTransparent(true);

        //create a 20 by 20 empty image to colorize
        WritableImage wi = new WritableImage(20, 20);

        //color each pixel in the image red
        PixelWriter pi = wi.getPixelWriter();
        for(int x = 0; x < 20; x++)
        {
            for(int y = 0; y < 20; y++)
            {
                pi.setColor(x, y, Color.color(1, 0, 0));
            }
        }
        //set the image to be the image that this instantitated object shows as. Mouseevents for this object will
        //be triggered and this image will act as the drag'n'drop controller of this object
        this.setImage(wi);
    }
    public Double getDragDeltaY()
    {
        return dragDeltaY;
    }

    public void setDragDeltaY(Double dragDeltaY)
    {
        this.dragDeltaY = dragDeltaY;
    }

    public Double getDragDeltaX()
    {
        return dragDeltaX;
    }

    public void setDragDeltaX(Double dragDeltaX)
    {
        this.dragDeltaX = dragDeltaX;
    }

    public Label getAttachedLabel()
    {
        return attachedLabel;
    }
}