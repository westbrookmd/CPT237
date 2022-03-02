//*********************************************
//
// Assignment 4
//
// Author: Marshall Westbrook
// Date: 3/1/22
//
// An application GUI to edit, load, and save binary files
//*********************************************

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class FileDump extends Application {

    public void start(Stage primaryStage) {

        // Create addressable controls
        // Reachable from the event handlers
        // Working Solution
        TextArea taInput = new TextArea("");
        taInput.setEditable(false);

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");
        menuBar.getMenus().addAll(fileMenu, helpMenu);


        Button btnUp = new Button("Up");
        btnUp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            }
        });
        Button btnDown = new Button("Down");
        btnDown.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            }
        });
        // Create the GridPane pane
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(5);
        pane.add(menuBar, 0, 0);
        //pane.add(menuBar, 0, 0);
        // Place nodes in the GridPane pane
        pane.add(new Label("Open File: None"), 0, 1);
        pane.add(taInput, 0, 2);

        // Create FlowPane pane
        FlowPane btnPane = new FlowPane();
        btnPane.setAlignment(Pos.CENTER);
        pane.setHgap(5);

        // Place nodes in the FlowPane pane and place
        // pane in the GridPane pane
        btnPane.setPadding(new Insets(10, 10, 10, 10));
        btnPane.setHgap(10);

        // Create buttons and event handlers

        // Clear Button
        Button btnClear = new Button("Clear");
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                taInput.setText("");
            }
        });

        Button btnExit = new Button("Exit");
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });

        // Place Buttons on the FlowPane and place FlowPane on GridPane
        btnPane.getChildren().addAll(btnUp, btnDown, btnClear, btnExit);
        pane.add(btnPane, 0, 3);

        // Place nodes in the GridPane pane
        //pane.add(new Label("Check result:"), 0, 3);
        //pane.add(taOutput, 0, 4);

        //Create scene and place it on the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("CPT 237 File Dump Utility");
        primaryStage.setWidth(450);
        primaryStage.setHeight(250);
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