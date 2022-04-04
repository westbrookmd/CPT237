//*********************************************
//
// Assignment 5
//
// Author: Marshall Westbrook
// Date: 3/7/22
//
// An application to edit, load, and save binary files
//*********************************************

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.util.Scanner;

public class FileDump extends Application
{
    //region Variables
    private File chosenFile;
    private RandomAccessFile raFile = null;
    private int fileIndex;
    private int fileOffset;
    private TextArea taOutput;
    //endregion
    public void start(Stage primaryStage)
    {
        //region TopMenuPane
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem fileOpen = new MenuItem("Open");
        MenuItem fileExit = new MenuItem("Exit");
        fileMenu.getItems().addAll(fileOpen, fileExit);
        Menu helpMenu = new Menu("Help");
        MenuItem helpAbout = new MenuItem("About");
        helpMenu.getItems().addAll(helpAbout);
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        //endregion
        //region CenterPane
        // Place nodes in the GridPane
        GridPane innerPane = new GridPane();
        // Needs some padding so that it isn't squished on all sides
        innerPane.setPadding(new Insets(10, 10, 10, 10));
        innerPane.add(new Label("Open File:"), 0, 0);
        // serves as the label for the current open file
        Label openFile = new Label("None");
        innerPane.add(openFile, 0, 1);
        // serves as the display for data
        taOutput = new TextArea("");
        taOutput.maxWidth(10);
        taOutput.maxHeight(80);
        taOutput.setWrapText(false);
        taOutput.setEditable(false);
        innerPane.add(taOutput, 0, 2);
        //endregion
        //region Buttons
        Button btnUp = new Button("Up");
        btnUp.setDisable(true);
        Button btnDown = new Button("Down");
        btnDown.setDisable(true);
        Button btnClear = new Button("Clear");
        btnClear.setDisable(true);
        Button btnExit = new Button("Exit");
        // Button Event Handlers
        btnUp.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                fileIndex--;
                if(fileIndex < 0)
                    fileIndex = 0;
                fileOffset = fileIndex*128;
                displayData(raFile, taOutput);
                if(fileOffset != 0)
                {
                    btnDown.setDisable(false);
                }
                if(fileIndex == 0)
                {
                    btnUp.setDisable(true);
                }
            }
        });
        btnDown.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                int tempOffset;
                fileIndex++;
                tempOffset = fileIndex*128;
                if(tempOffset <= chosenFile.length())
                {
                    fileOffset = tempOffset;
                    displayData(raFile, taOutput);
                    btnUp.setDisable(false);
                }
                else
                {
                    btnDown.setDisable(true);
                    fileIndex--;
                }
            }
        });
        btnClear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                taOutput.setText("");
                openFile.setText("None");
                btnClear.setDisable(true);
                btnDown.setDisable(true);
                btnUp.setDisable(true);
                chosenFile = null;
                raFile = null;
            }
        });
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        });
        //endregion
        //region Right VerticalPane
        VBox btnPane = new VBox();
        btnPane.setPadding(new Insets(10, 10, 10, 10));
        btnPane.setSpacing(10);
        btnPane.getChildren().addAll(btnUp, btnDown, btnClear, btnExit);
        //endregion
        //region ParentPane
        BorderPane pane = new BorderPane();
        pane.setTop(menuBar);
        pane.setCenter(innerPane);
        pane.setRight(btnPane);
        //endregion
        //region MenuLogic
        fileOpen.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                // Using FileChooser for the first time
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open File To Edit");
                chosenFile = fileChooser.showOpenDialog(primaryStage);
                if (chosenFile != null)
                {
                    try {
                        //set our class-wide variable
                        raFile = new RandomAccessFile(chosenFile.getAbsoluteFile(), "r");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    // setting all necessary variables for paging
                    fileIndex = 0;
                    fileOffset = 0;
                    // Inform the user what's available to click
                    btnUp.setDisable(true);
                    // TODO: check if the file size is large enough
                    btnDown.setDisable(false);
                    btnClear.setDisable(false);
                    openFile.setText(chosenFile.getName());
                    displayData(raFile, taOutput);
                }
            }
        });
        helpAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "By Marshall Westbrook, a junior software developer.", ButtonType.CLOSE);
                alert.setTitle("About me");
                alert.setHeaderText("About The Developer");
                alert.showAndWait();
            }
        });
        fileExit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        //endregion
        //region WindowSettings
        Scene scene = new Scene(pane);
        primaryStage.setTitle("CPT 237 File Dump Utility");
        primaryStage.setWidth(550);
        primaryStage.setHeight(280);
        primaryStage.setScene(scene);
        primaryStage.show();
        //endregion
    }
    //From FileDump with minor changes
    private void displayData(RandomAccessFile file, TextArea fileOutput)
    {
        DecimalFormat two = new DecimalFormat("00");
        DecimalFormat four = new DecimalFormat("0000");
        StringBuffer buffer = new StringBuffer(480);
        StringBuffer ascii = null;
        int offset = fileOffset;
        byte byteValue;
        String hexValue;

        try
        {
            file.seek(fileOffset);
        }
        catch(Exception sekExec)
        {
            fileOutput.setText("Error with seeking on the file.");
        }

        try
        {
            for(int i=0; i<8; i++)
            {
                ascii = new StringBuffer();
                hexValue = Integer.toHexString(offset);
                while(hexValue.length()<4)
                    hexValue = "0" + hexValue;
                buffer.append(hexValue);
                buffer.append("   ");
                for(int j=0; j<16; j++)
                {
                    byteValue = file.readByte();
                    hexValue = Integer.toHexString(byteValue);
                    if(byteValue < 32)
                        ascii.append('.');
                    else
                        ascii.append((char) byteValue);
                    if(hexValue.length() == 1)
                        hexValue = "0" + hexValue;
                    buffer.append(hexValue.substring(0,2));
                    buffer.append(' ');
                }
                buffer.append("    ");
                buffer.append(ascii);
                buffer.append('\n');
                offset += 16;
            }
        }
        catch(Exception readExec)
        {
            buffer.append("EOF");
        }
        fileOutput.setText(buffer.toString());
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}