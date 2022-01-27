//*********************************************
//
// Assignment 2
//
// Author: Marshall Westbrook
// Date: 1/24/22
//
// Encryption application that throws exceptions
// and handles them gracefully.
//
// Validation should really be included in the
// file that processes the data in addition to
// the UI. This assignment required editing
// the EncryptionApplication, so I just edited this
// file.
//
//*********************************************

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class EncryptionApplication extends Application {

    public void start(Stage primaryStage) {

        // Create addressable controls
        // Reachable from the event handlers
        TextArea taInput = new TextArea("Input text here");
        TextArea taOutput = new TextArea("Output Text:");
        String userException = "Exception encountered. Check the console for details \n" +
                                "Please don't include any characters that are not between a" +
                                "- z or is a space.";

        // Create the GridPane pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);

        // Place nodes in the GridPane pane
        pane.add(new Label("Input Text:"), 0, 0);
        pane.add(taInput, 0, 1);

        // Create FlowPane pane
        FlowPane btnPane = new FlowPane();
        btnPane.setAlignment(Pos.CENTER);
        pane.setHgap(5);

        // Place nodes in the FlowPane pane and place
        // pane in the GridPane pane
        btnPane.setPadding(new Insets(10, 10, 10, 10));
        btnPane.setHgap(10);

        // Create buttons and event handlers
        // Encrypt Button
        Button btnEncrypt = new Button("Encrypt");
        btnEncrypt.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // Get input field and create EncryptString object
                if(ValidateInput(taInput.getText()))
                {
                    EncryptString eObj = new EncryptString(taInput.getText());
                    // Output the encrypted text
                    taOutput.setText(eObj.encryptString());
                }
                else
                {
                    taInput.setText(userException);
                }
            }
        });
        // Clear Button
        Button btnClear = new Button("Clear");
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                taInput.setText("");
                taOutput.setText("");
            }
        });

        // Decrypt Button
        Button btnDecrypt = new Button("Decrypt");
        btnDecrypt.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(ValidateInput(taInput.getText()))
                {
                    // Get the encrypted string from input field
                    String eString = taInput.getText();
                    // Output the encrypted text
                    taOutput.setText(EncryptString.decryptString(eString));
                }
                else
                {
                    taInput.setText(userException);
                }
            }
        });

        // Place Buttons on the FlowPane and place FlowPane on GridPane
        btnPane.getChildren().addAll(btnEncrypt, btnClear, btnDecrypt);
        pane.add(btnPane, 0, 2);

        // Place nodes in the GridPane pane
        pane.add(new Label("Output Text:"), 0, 3);
        pane.add(taOutput, 0, 4);

        //Create scene and place it on the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("CPT 236 Encryption Application");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static boolean ValidateInput(String textBoxData)
    {
        try{
            for(int i=0; i < textBoxData.length() ; i++)
            {
                char charToCheck = textBoxData.charAt(i);
                if(!(charToCheck > 'a' && charToCheck < 'z' || charToCheck > 'A' && charToCheck < 'Z' || charToCheck == ' '))
                {
                    throw new Exception("Character found that is not between a - z or is a space.");
                }
            }
            return true;
        }catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Exception Handled");
            alert.setHeaderText("Exception Encountered: Character Error");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
        return false;
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}