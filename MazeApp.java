import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;

//Project1054 by Karis Fisher

public class MazeApp extends Application {
   
   //start method
   public void start(Stage stage) {
   
   //create flowpane
   FlowPane fp  = new FlowPane();
   
   //create canvas
   MazeCanvas theCanvas = new MazeCanvas();

      //set flowpane alignment
      fp.setAlignment(Pos.CENTER);
      
      //add canvas to flowpane
      fp.getChildren().add(theCanvas);
   
      //set the scene
      Scene scene = new Scene(fp, 525, 525);
      stage.setScene(scene);
      stage.setTitle("Maze Game");
      stage.show();
      
      //request focus on the canvas
      theCanvas.requestFocus();
   }
   
   //main launch method
   public static void main(String[] args){
      launch(args);
   }     
}