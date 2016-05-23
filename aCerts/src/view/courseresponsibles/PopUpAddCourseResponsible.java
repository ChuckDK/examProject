package view.courseresponsibles;

import control.operations.SQLOperations;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.courses.PopUpAddCoursePersona;
import view.courses.PopUpCourseSelectionList;
import view.styling.ACertsColorScheme;

public class PopUpAddCourseResponsible extends PopUpAddCoursePersona
{

    private CheckBox adminRights;
    private Label adminRightsLabel;
    private Button addCourseResponsible;
    private PasswordField passwordField;

    public PopUpAddCourseResponsible()
    {
        super();

        adminRights = new CheckBox();

        passwordField = new PasswordField();

        adminRightsLabel = new Label("Admin rights");

        addCourseResponsible = new Button("Add course responsible");

        //layouting
        adminRights.setLayoutX(320);
        adminRights.setLayoutY(210);

        adminRightsLabel.setLayoutX(350);
        adminRightsLabel.setLayoutY(210);

        addCourseResponsible.setLayoutX(30);
        addCourseResponsible.setLayoutY(350);

        passwordField.setLayoutX(100);
        passwordField.setLayoutY(300);

        //Add elements to view
        this.getChildren().addAll(
                adminRights,
                adminRightsLabel,
                addCourseResponsible,
                passwordField);

        //styling
        addCourseResponsible.setStyle(ACertsColorScheme.buttonColor());
        adminRights.setStyle(ACertsColorScheme.buttonColor());

        //functionality
    }

    public boolean checkForValues()
    {
        boolean allValuesFilledOut = true;
        DropShadow error = new DropShadow();
        error.setColor(Color.RED);

        if(emailTextField.getText().equals(""))
        {
            emailTextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            emailTextField.setEffect(null);
        }

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
            phoneNumber2TextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            phoneNumber2TextField.setEffect(null);
        }
        if(firstNameTextField.getText().equals(""))
        {
            firstNameTextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            firstNameTextField.setEffect(null);
        }
        if(lastNameTextField.getText().equals(""))
        {
            lastNameTextField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            lastNameTextField.setEffect(null);
        }
        if(passwordField.getText().equals(""))
        {
            passwordField.setEffect(error);
            allValuesFilledOut = false;
        }
        else
        {
            passwordField.setEffect(null);
        }
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
}
