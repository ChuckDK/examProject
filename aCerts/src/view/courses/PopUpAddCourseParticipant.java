package view.courses;

import control.operations.SQLOperations;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coursedata.Course;
import view.styling.ACertsColorScheme;

public class PopUpAddCourseParticipant extends PopUpAddCoursePersona {
    private Button addCourseParticipant;
    private Button assignCourse;
    private Course assignedCourse;

    public PopUpAddCourseParticipant()
    {
        //Call the PopUpAddCoursePersona class to get the GUI for the class.
        super();

        //Even though the GUI has already been implemented,
        //it is still needed to add some buttons dedicated to this class
        addCourseParticipant = new Button("Add course participant");
        assignCourse = new Button("Assign Course");

        //Set the position of the buttons.
        addCourseParticipant.setLayoutX(30);
        addCourseParticipant.setLayoutY(350);

        assignCourse.setLayoutX(270);
        assignCourse.setLayoutY(140);

        //Add functionality to the assignCourseButton.
        assignCourse.setOnAction(e->
        {
            //Making a new stage and a scene based on PopUpCourseSelectionList.
            Stage popup = new Stage();
            popup.setTitle("Select Course");
            PopUpCourseSelectionList popupPane = new PopUpCourseSelectionList();
            popup.setScene(new Scene(popupPane, 600, 400));

            //Reuse the cancelButton from PopUpCourseSelectionList and make it close the window when clicked.
            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            //Reuse the selectCoursesButton from PopUpCourseSelectionList
            //and set it to make the selected course equal to the assignedCourse course variable.
            ((Button) popupPane.getChildren().get(3)).setOnAction(ex->
            {
                assignedCourse = ((TableView<Course>) popupPane.getChildren().get(1)).getSelectionModel().getSelectedItem();
                popup.close();
            });

            //These two lines creates the popup window effect
            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });

        //Add the
        this.getChildren().addAll(addCourseParticipant, assignCourse);

        //Style the buttons with ACertsColorScheme.
        addCourseParticipant.setStyle(ACertsColorScheme.buttonColor());
        assignCourse.setStyle(ACertsColorScheme.buttonColor());
    }

    //Method which ensures all the values has been added before they are added to the database.
    public boolean checkForValues()
    {
        //Initialize a boolean and a DropShadow, and style the DropShadow.
        boolean allValuesFilledOut = true;
        DropShadow error = new DropShadow();
        error.setColor(Color.RED);

        //If the email text field is empty, set the DropShadow effect on the emailTextField text field,
        //set the allValuesFilledOut to false.
        if(emailTextField.getText().equals(""))
        {
            emailTextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            emailTextField.setEffect(null);
        }

        //If the phone number text field is empty, set the DropShadow effect on the phoneNumberTextField text field,
        //set the allValuesFilledOut to false.
        if(phoneNumberTextField.getText().equals(""))
        {
            phoneNumberTextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            phoneNumberTextField.setEffect(null);
        }

        //If the phone number 2 text field is empty, set the DropShadow effect on the phoneNumber2TextField text field,
        //set the allValuesFilledOut to false.
        if(phoneNumber2TextField.getText().equals(""))
        {
            phoneNumber2TextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            phoneNumber2TextField.setEffect(null);
        }

        //If the first name text field is empty, set the DropShadow effect on the firstNameTextField text field,
        //set the allValuesFilledOut to false.
        if(firstNameTextField.getText().equals(""))
        {
            firstNameTextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            firstNameTextField.setEffect(null);
        }

        //If the last name text field is empty, set the DropShadow effect on the lastNameTextField text field,
        //set the allValuesFilledOut to false.
        if(lastNameTextField.getText().equals(""))
        {
            lastNameTextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            lastNameTextField.setEffect(null);
        }

        //If the assignedCourse course object is null, set the DropShadow effect on the assignCourse button,
        //set the allValuesFilledOut to false.
        if(assignedCourse == null)
        {
            allValuesFilledOut = false;
            assignCourse.setEffect(error);
        }
        else
        {
            assignCourse.setEffect(null);
        }

        //If allValuesFilledOut is true, which essentially means every other field was filled in correctly,
        //then take the filled in info and add to the database.
        if(allValuesFilledOut)
        {
            String[] values = {
                    "'" + emailTextField.getText() + "'",
                    "'" + firstNameTextField.getText() + "'",
                    "'" + lastNameTextField.getText() + "'"
            };
            SQLOperations.addNewRow("course_participants", values);

            String[] phoneValues = {
                    "'" + emailTextField.getText() + "'",
                    "'Primary'",
                    "'" + phoneNumberTextField.getText() + "'"
            };
            SQLOperations.addNewRow("phones_course_participants", phoneValues);

            String[] phone2Values = {
                    "'" + emailTextField.getText() + "'",
                    "'Secondary'",
                    "'" + phoneNumber2TextField.getText() + "'"
            };
            SQLOperations.addNewRow("phones_course_participants", phone2Values);

            String[] certificateValues = {
                    ""+assignedCourse.getCourseID(),
                    "'" + emailTextField.getText() + "'",
                    "false"
            };
            SQLOperations.addNewRow("certificates", certificateValues);
        }
        return allValuesFilledOut;
    }
}
