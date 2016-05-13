package model;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * Created by dennis on 5/13/16.
 */
public class DragableLabel extends ImageView{

    private Label attachedLabel;
    private double dragDeltaX;
    private double dragDeltaY;

    public DragableLabel(String name) {
        attachedLabel.setText(name);
    }

    public Label getAttachedLabel() {
        return attachedLabel;
    }

    public void setAttachedLabel(Label attachedLabel) {
        this.attachedLabel = attachedLabel;
    }

    public double getDragDeltaX() {
        return dragDeltaX;
    }

    public void setDragDeltaX(double dragDeltaX) {
        this.dragDeltaX = dragDeltaX;
    }

    public double getDragDeltaY() {
        return dragDeltaY;
    }

    public void setDragDeltaY(double dragDeltaY) {
        this.dragDeltaY = dragDeltaY;
    }
}
