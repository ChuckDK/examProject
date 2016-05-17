package view;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.scene.Cursor;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DragableLabel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;

/**
 * Created by dennis on 5/13/16.
 */
public class PopUpCourseCertificateTemplateGeneratorAdmin extends Pane implements Resizable{
    //to be repurposed in future
    private Button generate = new Button("Generate Certificate");


    //Pane elements are defined here

    //editoreview makes the editor scrollable. editor is contained inside editorview and is where all the elements in
    //editor view are
    private ScrollPane editorView = new ScrollPane();
    private Pane editor = new Pane();

    //:::::::::::::::editor view elements::::::::::::::::::

    //Labels with attached drag'n'drop controller object
    private DragableLabel name = new DragableLabel("Name");
    private DragableLabel date = new DragableLabel("date");

    //The background image
    private ImageView certificateImageView = new ImageView();

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::

    //:::::::::::::::editor sidepanel elements:::::::::::::

    //labels to help the user know what the textfields does
    private Label size1 = new Label("Size:");
    private Label size2 = new Label("Size:");
    private Label nameLabel = new Label("Sample Name:");
    private Label dateLabel = new Label("Sample Date:");

    //textfields to edit name and date values to display in the editorview
    private TextField editName = new TextField("Name");
    private TextField editDate = new TextField();

    //textfields to edit the fontsize of the name and date drag'n'drop labels
    private TextField sizeName = new TextField();
    private TextField sizeDate = new TextField();

    //Colorpickers to edit the color of the name and date drag'n'drop labels
    private ColorPicker nameColor = new ColorPicker();
    private ColorPicker dateColor = new ColorPicker();

    //button to load an image as a background image in the editorview
    private Button loadButton = new Button("Load Image");

    //Button to exit the editor without saving the template
    private Button cancelButton = new Button("Cancel");

    //:::::::::::::::::::::::::::::::::::::::::::::::::::::

    public PopUpCourseCertificateTemplateGeneratorAdmin()
    {
        //add all sidemenu elements of to this pane so that they are shown
        this.getChildren().addAll(
                cancelButton,
                editorView,
                generate,
                editName,
                editDate,
                nameLabel,
                dateLabel,
                loadButton,
                size1,
                sizeName,
                size2,
                sizeDate,
                nameColor,
                dateColor);

        //add all the elements of the editorview. the getAttached methods are for getting elements stored in the
        //DragableLabel objects to be shown in this editorview
        editor.getChildren().addAll(
                certificateImageView,
                name,
                date,
                name.getAttachedLabel(),
                date.getAttachedLabel());

        //set the editor pane to be a child of editorView. this makes the editor pane scrollable if it cannot be shown
        //fully
        editorView.setContent(editor);

        //styling of the certificate editor
        this.setStyle(ACertsColorScheme.viewColor());
        editorView.setStyle(ACertsColorScheme.subViewColor());
        loadButton.setStyle(ACertsColorScheme.buttonBcolor());
        editDate.setStyle(ACertsColorScheme.textFieldColor());
        editName.setStyle(ACertsColorScheme.textFieldColor());
        sizeDate.setStyle(ACertsColorScheme.textFieldColor());
        sizeName.setStyle(ACertsColorScheme.textFieldColor());
        generate.setStyle(ACertsColorScheme.buttonBcolor());
        cancelButton.setStyle(ACertsColorScheme.buttonBcolor());

        //events for various interactions with pane elements
        loadButton.setOnAction(e ->loadImage());
        generate.setOnAction(e-> generateImage());
        sizeName.setOnKeyPressed(e-> updateFontSize(e, name, sizeName, editName));
        sizeDate.setOnKeyPressed(e-> updateFontSize(e, date, sizeDate, editDate));
        editName.setOnKeyPressed(e-> updateString(e, name, editName));
        editDate.setOnKeyPressed(e-> updateString(e, date, editDate));
        nameColor.setOnAction(e->name.getAttachedLabel().setTextFill(nameColor.getValue()));
        dateColor.setOnAction(e->date.getAttachedLabel().setTextFill(dateColor.getValue()));

        //pane elements that does not need to altered when the window is sized. If the elements layout property is
        //change here that means that  that property will only be set on creation of the certificate editor and
        //never be altered again

        editName.setLayoutX(10);
        editName.setLayoutY(30);
        editName.setPrefWidth(180);

        nameColor.setLayoutX(200);
        nameColor.setLayoutY(60);
        nameColor.setPrefWidth(90);

        nameLabel.setLayoutX(10);
        nameLabel.setLayoutY(10);

        editDate.setLayoutX(10);
        editDate.setLayoutY(120);
        editDate.setPrefWidth(180);

        dateColor.setLayoutX(200);
        dateColor.setLayoutY(150);
        dateColor.setPrefWidth(90);

        dateLabel.setLayoutX(10);
        dateLabel.setLayoutY(100);

        editorView.setLayoutX(300);
        editorView.setLayoutY(10);

        loadButton.setLayoutX(10);
        loadButton.setLayoutY(180);

        generate.setLayoutX(10);
        cancelButton.setLayoutX(200);

        //set a sample date for the date label

        //create an instance of a Calendar object. when instanciated, all time related information is set to the time
        //right now, f.ex. if today is christmas eve in the year 2016 the date is set to 24/12-2016. If the time of day
        //is twelve minutes past  5 o clock in the afternoon, the time is then set to 17:12
        Calendar now = Calendar.getInstance();

        //get the information from the calendar object about the date of this moment and store them in the date drag'n'drop
        //label and in the textfield for editing the date drag'n'drop label
        date.getAttachedLabel().setText(""+now.get(Calendar.DATE)+"/"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.YEAR));
        editDate.setText(""+now.get(Calendar.DATE)+"/"+now.get(Calendar.MONTH)+"-"+now.get(Calendar.YEAR));

        //this method will set mouse events that needs to access elements from this pane that makes them able to be
        //dragged around with the mouse cursor
        makeDraggable(name);
        makeDraggable(date);

        //set the textfield for editing the font sizes to be the current size of the labels
        sizeName.setText(""+name.getAttachedLabel().getFont().getSize());
        sizeDate.setText(""+date.getAttachedLabel().getFont().getSize());

    }


    //methods that modifies the layout of certain elements to match with the current size of the window.
    @Override
    public void updateLayout()
    {
        //sets the editorviews size to be inside the current window with a little spacing added. 310 is the size of
        //the editor sidemenu (300) plus a margin of 10.
        editorView.setPrefWidth(this.getScene().getWidth() - 310);

        //20 represents a margin of 10 at the top and at the bottom
        editorView.setPrefHeight(this.getScene().getHeight() - 20);

        size1.setLayoutX(200);
        size1.setLayoutY(33);

        sizeName.setLayoutX(210 + size1.getWidth());
        sizeName.setLayoutY(30);
        sizeName.setPrefWidth(290 - (210 + size1.getWidth()));

        size2.setLayoutX(200);
        size2.setLayoutY(123);

        sizeDate.setLayoutX(210 + size2.getWidth());
        sizeDate.setLayoutY(120);
        sizeDate.setPrefWidth(290 - (210 + size2.getWidth()));
        generate.setLayoutY(this.getScene().getHeight() - generate.getHeight() - 10);
        cancelButton.setLayoutY(this.getScene().getHeight() - cancelButton.getHeight() - 10);
    }





    //****************************************************************************************************************
    //to be moved to backend functionality. this editors final purpose is to generate templates and not the certificates themselves
    //****************************************************************************************************************
    public void generateImage()
    {
        WritableImage snapshotImage = editor.snapshot(new SnapshotParameters(), null);
        try
        {
            ImageIO.write(SwingFXUtils.fromFXImage(snapshotImage, null), "png", new File(name.getAttachedLabel().getText() +".png"));
        }
        catch(Exception exc)
        {
            System.out.println("shiiiet");
        }
    }

    //****************************************************************************************************************





    //this method update the text on the drag'n'drop labels and adjusts their position relative to their drag'n'drop controller
    public void updateString(KeyEvent ke, DragableLabel label, TextField textField) //to be merged with method below
    {
        if(ke.getCode() == KeyCode.ENTER)
        {
            //update the text on the label
            label.getAttachedLabel().setText(textField.getText());

            //due to limitations in javafx we are forced to use the following method to calculate the width
            //of the label, to put it centered on the drag'n'drop controller
            float width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(
                    textField.getText(),
                    label.getAttachedLabel().getFont());

            //move the label  to the left of the drag'n'drop controller equal to half of the labels width, making it centered
            //on the drag'n'drop controller (10 represents half of the size of the drag'n'drop controller which means that
            //the label will be centered on the center of the drag'n'drop controller
            label.getAttachedLabel().setTranslateX(label.getTranslateX() - width / 2 + 10);
        }
    }

    //this method updates the fontsize of the labels and alters their position relative to related drag'n'drop controller
    public void updateFontSize(KeyEvent ke, DragableLabel label, TextField size, TextField text)
    {
        if(ke.getCode() == KeyCode.ENTER)
        {
            try
            {
                //set the fontsize of the label to the value the user input in corresponding textfield
                label.getAttachedLabel().setFont(Font.font(Double.parseDouble(size.getText())));

                //due to limitations in javafx we are forced to use the following method to calculate the width and height
                //of the label, to put it centered on the drag'n'drop controller
                float width = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().computeStringWidth(text.getText(), label.getAttachedLabel().getFont());
                float height = com.sun.javafx.tk.Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.font(Double.parseDouble(size.getText()))).getLineHeight();

                //move the label  to the left and north of the drag'n'drop controller equal to half of the labels width and height
                //, making it centered on the drag'n'drop controller
                label.getAttachedLabel().setTranslateX(label.getTranslateX() - width / 2 + 10);
                label.getAttachedLabel().setTranslateY(label.getTranslateY() - height / 2 + 10);
            }
            catch (Exception ex)
            {
                //lets the user know that the input of the textfield is not a number
                size.setText("error");
            }
        }
    }

    //Opens a a file explorer window to choose an image file to load into the editor
    public void loadImage()
    {
        //create instance of filechooser object which is essentially a file explorer
        FileChooser fileChooser = new FileChooser();

        //set title of the filechooser window
        fileChooser.setTitle("Load image");

        //setup filechooser object to only show png and jpg files in the file explorer
        FileChooser.ExtensionFilter images = new FileChooser.ExtensionFilter("Images", "*.JPG", "*.jpg", "*.png", "*.PNG");
        fileChooser.getExtensionFilters().addAll(images);

        //store the file chosen from the file explorer in a File object
        File file = fileChooser.showOpenDialog(new Stage());

        //try and read the file as an image, if it fails the jump to catch
        try
        {
            //read and store the file as a BufferedImage object (BufferedImage is a swing class
            BufferedImage image = ImageIO.read(file);

            //convert the BufferedImage to a javaFX image and show it in the editor, by setting the certificateImageViews
            // image to be the one converted from the BufferedImage
            certificateImageView.setImage(SwingFXUtils.toFXImage(image, null));

            //set width and heigth of the editor pane to the size of the image
            editor.setPrefWidth(image.getWidth());
            editor.setPrefHeight(image.getHeight());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    //sets the DragableLabel to have mouseevents that needs information from this class (the size of the editorview scrollpane)
    public void makeDraggable(DragableLabel node)
    {
        //this event makes the mouse icon change to a closed hand to symbolise that the mouse have grabbed this label
        //and can move it around.
        node.setOnMousePressed(event ->
        {
            node.setCursor(Cursor.CLOSED_HAND);

            //TO BE RESEARCHED
            node.setDragDeltaX(node.getLayoutX() + event.getX());
            node.setDragDeltaY(node.getLayoutY() + event.getY());
        });

        //this event moves the currently grabbed label
        node.setOnMouseDragged(event ->
        {
            //store information about how much is scrolled in the editorview and how much is visible in the editorview
            Bounds bounds = editorView.getViewportBounds();

            //invert the information about how much is scrolled in the editorview
            int x = -1 * (int)bounds.getMinX() + 1;
            int y = -1 * (int)bounds.getMinY() + 1;

            //move the drag'n'drop controller for the label
            //TO BE RESEARCHED
            node.setTranslateX(event.getSceneX() - node.getDragDeltaX() - 300 + x);
            node.setTranslateY(event.getSceneY() - node.getDragDeltaY() - 10 + y);

            //move the attached label relative to the drag'n'drop controller
            node.getAttachedLabel().setTranslateX(node.getTranslateX() - node.getAttachedLabel().getWidth() / 2 + 10);
            node.getAttachedLabel().setTranslateY(node.getTranslateY() - node.getAttachedLabel().getHeight() / 2 + 10);
        });

        //this events changes the mouse cursor to be an open hand to symbolize that this element can be grabbed with the mouse
        node.setOnMouseEntered(event -> node.setCursor(Cursor.OPEN_HAND));
    }
}
