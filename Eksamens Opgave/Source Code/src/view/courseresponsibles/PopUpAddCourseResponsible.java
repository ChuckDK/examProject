package view.courseresponsibles;

import control.operations.SQLOperations;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.coursedata.CourseResponsible;
import view.courses.PopUpAddCoursePersona;
import view.courses.PopUpCourseSelectionList;
import view.styling.ACertsColorScheme;

public class PopUpAddCourseResponsible extends PopUpAddCoursePersona
{

    private CheckBox adminRights;
    private Label adminRightsLabel;
    private Label passwordLabel;
    private Button addCourseResponsible;
    private PasswordField passwordField;

    public PopUpAddCourseResponsible()
    {
        //Inheriting the elements from the PopUpAddCoursePersona class
        super();

        //Initializing a check box.
        adminRights = new CheckBox();

        //Initializing the passwordField and set it's prompt text.
        passwordField = new PasswordField();
        passwordField.setPromptText("Insert Password...");

        //Initializing the password label.
        passwordLabel = new Label("Password:");

        //Initializing the adminRightsLabel label.
        adminRightsLabel = new Label("Admin rights");

        //Initializing the addCourseResponsible button
        addCourseResponsible = new Button("Add course responsible");

        //Set the position of the new initialized element.
        adminRights.setLayoutX(320);
        adminRights.setLayoutY(210);

        adminRightsLabel.setLayoutX(350);
        adminRightsLabel.setLayoutY(210);

        addCourseResponsible.setLayoutX(30);
        addCourseResponsible.setLayoutY(350);

        passwordField.setLayoutX(100);
        passwordField.setLayoutY(300);

        passwordLabel.setLayoutX(30);
        passwordLabel.setLayoutY(305);

        //Add elements to view.
        this.getChildren().addAll(
                adminRights,
                adminRightsLabel,
                addCourseResponsible,
                passwordField,
                passwordLabel);

        //Style the elements using ACertsColorScheme.
        addCourseResponsible.setStyle(ACertsColorScheme.buttonColor());
        adminRights.setStyle(ACertsColorScheme.buttonColor());
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

        if(phoneNumber2TextField.getText().equals(""))
        {
            phoneNumber2TextField.setText("0");
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

        //If the password text field is empty, set the DropShadow effect on the lastNameTextField text field,
        //set the allValuesFilledOut to false.
        if(passwordField.getText().equals(""))
        {
            passwordField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            passwordField.setEffect(null);
        }

        //If allValuesFilledOut is true, which essentially means every other field was filled in correctly,
        //then take the filled in info and add to the database.
        if (allValuesFilledOut)
        {
            String bool = "false";
            if(adminRights.isSelected())
            {
                bool = "true";
            }
            String[] values = {
                    "'"+emailTextField.getText()+"'",
                    "'"+firstNameTextField.getText()+"'",
                    "'"+lastNameTextField.getText()+"'",
                    bool,
                    "'"+passwordField.getText()+"'"
            };
            SQLOperations.addNewRow("course_responsibles", values);

            String[] phoneValues = {
                    "'"+emailTextField.getText()+"'",
                    "'Primary'",
                    "'"+phoneNumberTextField.getText()+"'"
            };
            SQLOperations.addNewRow("phones_course_responsibles", phoneValues);

            String[] phone2Values = {
                    "'"+emailTextField.getText()+"'",
                    "'Secondary'",
                    "'"+phoneNumber2TextField.getText()+"'"
            };
            SQLOperations.addNewRow("phones_course_responsibles", phone2Values);
        }
        return allValuesFilledOut;
    }

    public CourseResponsible getCourseResponsible()
    {
        return new CourseResponsible(
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                phoneNumberTextField.getText(),
                phoneNumber2TextField.getText(),
                emailTextField.getText(),
                adminRights.isSelected());
    }
}
