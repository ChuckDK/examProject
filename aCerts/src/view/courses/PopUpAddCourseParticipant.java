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
        super();
        addCourseParticipant = new Button("Add course participant");
        assignCourse = new Button("Assign Course");

        addCourseParticipant.setLayoutX(30);
        addCourseParticipant.setLayoutY(350);

        assignCourse.setLayoutX(270);
        assignCourse.setLayoutY(140);

        assignCourse.setOnAction(e->
        {
            Stage popup = new Stage();
            popup.setTitle("Select Course");
            PopUpCourseSelectionList popupPane = new PopUpCourseSelectionList();
            popup.setScene(new Scene(popupPane));

            ((Button) popupPane.getChildren().get(0)).setOnAction(ex->popup.close());

            ((Button) popupPane.getChildren().get(3)).setOnAction(ex->
            {
                assignedCourse = ((TableView<Course>) popupPane.getChildren().get(1)).getSelectionModel().getSelectedItem();
                popup.close();
            });

            popup.initModality(Modality.APPLICATION_MODAL);
            popup.showAndWait();
        });

        this.getChildren().addAll(addCourseParticipant, assignCourse);

        //styling
        addCourseParticipant.setStyle(ACertsColorScheme.buttonColor());
        assignCourse.setStyle(ACertsColorScheme.buttonColor());
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
        if(assignedCourse == null)
        {
            allValuesFilledOut = false;
            assignCourse.setEffect(error);
        }
        else
        {
            assignCourse.setEffect(null);
        }
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
