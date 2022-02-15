//*********************************************
//
// Assignment 3
//
// Author: Marshall Westbrook
// Date: 2/14/22
//
// An application to help check for sudoku
// solutions. This does not solve them for
// the user, but does verify answers.
// Application starts with a working solution.
//
// I don't like how I implemented the validator
// class. This seems like a poor way to use
// objects since every check is creating a
// new object. I implemented text styles for
// the output text and the 'check' gives the
// 2d array's last incorrect number.
//
// Future considerations:
// 1)Scrap the validator class and add a cleaner
// solution. This works, but it would be terrible for
// others to use properly. It feels bloated.
// 2)Add bulk processing support and make the check
// function asynchronous
// 3)Add a listener that does real-time checks of
// the data. --Similar to how spell-check works
// 4)Add settings for editing the style of the
// application and also settings to change the
// behavior of the checker. Maybe it allows numbers
// greater than 9 or allows for different separators
// rather than just spaces.
//*********************************************

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class SudokuCheckApplication extends Application {

    public void start(Stage primaryStage) {

        // Create addressable controls
        // Reachable from the event handlers
        // Working Solution
        TextArea taInput = new TextArea("5 3 4 6 7 8 9 1 2\n" +
                "6 7 2 1 9 5 3 4 8\n" +
                "1 9 8 3 4 2 5 6 7\n" +
                "8 5 9 7 6 1 4 2 3\n" +
                "4 2 6 8 5 3 7 9 1\n" +
                "7 1 3 9 2 4 8 5 6\n" +
                "9 6 1 5 3 7 2 8 4\n" +
                "2 8 7 4 1 9 6 3 5\n" +
                "3 4 5 2 8 6 1 7 9");

        TextArea taOutput = new TextArea("Waiting for tests...");
        taOutput.setWrapText(true);
        taOutput.setEditable(false);
        taOutput.setStyle("-fx-text-fill: grey; -fx-font-size: 16px;"); //css styling
        // Create the GridPane pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);

        // Place nodes in the GridPane pane
        pane.add(new Label("Input Sudoku:"), 0, 0);
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
        // Check Button
        Button btnCheck = new Button("Check");
        btnCheck.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                taOutput.setText("");
                // take the text from taInput.getText() and use a multidimensional array to process the data
                SudokuValidator helper = new SudokuValidator(taInput.getText());
                // Output the result to taOutput.setText("the output string here");
                //check for errors
                if(!helper.formatError.isBlank())
                {
                    taOutput.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
                    taOutput.setText("Formatting exception. Check your input and review the instructions.");
                }
                //check for wrong numbers
                else if (helper.GetBadColumn() != 10 && helper.GetBadRow() != 10)
                {
                    //translate to non-zero based counting
                    int badX = helper.GetBadRow() + 1;
                    int badY = helper.GetBadColumn() + 1;
                    taOutput.setStyle("-fx-text-fill: red; -fx-font-size: 12px;");
                    taOutput.setText("Bad Number on row " + badX + " and column " + badY);
                }
                //check for a perfect answer
                else if (helper.GetBadColumn() == 10 && helper.GetBadRow() == 10)
                {
                    taOutput.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
                    taOutput.setText("All tests passed!");
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

        Button btnHelp = new Button("Help");
        btnHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                final Stage dialog = new Stage();
                dialog.setTitle("Helpful Instructions");
                dialog.initModality(Modality.APPLICATION_MODAL);
                dialog.initOwner(primaryStage);
                VBox dialogVbox = new VBox(400);
                TextArea instructions = new TextArea(
                        "Enter in your sudoku by typing the numbers in order " +
                                "and separating each number by a single space. You " +
                                "may copy and paste data from a notepad file and the " +
                                "textarea should interpret the new line characters. \n" +
                                "Here is an example solution:\n" +
                                "8 5 7 3 9 2 4 1 6\n" +
                                "2 1 4 8 5 6 3 7 9\n" +
                                "9 3 6 1 4 7 2 8 5\n" +
                                "5 6 8 4 2 9 1 3 7\n" +
                                "4 9 2 7 3 1 6 5 8\n" +
                                "1 7 3 6 8 5 9 4 2\n" +
                                "3 2 1 5 6 8 7 9 4\n" +
                                "6 4 5 9 7 3 8 2 1\n" +
                                "7 8 9 2 1 4 5 6 3");
                instructions.setWrapText(true);
                instructions.setEditable(false);
                instructions.setPrefHeight(400);
                dialogVbox.getChildren().add(instructions);
                Scene dialogScene = new Scene(dialogVbox, 400, 400);
                dialog.setScene(dialogScene);
                dialog.show();
            }
        });

        // Place Buttons on the FlowPane and place FlowPane on GridPane
        btnPane.getChildren().addAll(btnCheck, btnClear, btnHelp);
        pane.add(btnPane, 0, 2);

        // Place nodes in the GridPane pane
        pane.add(new Label("Check result:"), 0, 3);
        pane.add(taOutput, 0, 4);

        //Create scene and place it on the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("CPT 237 Sudoku Checker");
        primaryStage.setWidth(350);
        primaryStage.setHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}