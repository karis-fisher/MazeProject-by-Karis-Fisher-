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
public class MazeCanvas extends Canvas {

   //create 2D array for the maze and graphics context
   int maze[][] = new int[21][21];
   GraphicsContext gc = getGraphicsContext2D();
   
   //position of blue box
   int pX = 0;
   int pY = 0;
   
   //create constructor
   public MazeCanvas() {
   
      //try statement
      try {
      
         //scan in the maze file
         Scanner scan = new Scanner(new File("mazeFile.txt"));
         
         //call the listener
         setOnKeyPressed(new KeyListenerDown());
         
         //loop through file and add the numbers to the maze array
         for(int x = 0; x < 21; x++) {
            for(int y = 0; y < 21; y++) {
               int number = scan.nextInt();
               maze[y][x] = number;
            }
         }
      }
      
      //catch file not found exception
      catch(FileNotFoundException fnfe) {
         System.out.println("File not found");
      }
      
      //set the scene
      setWidth(525);
      setHeight(525);
      GraphicsContext gc = getGraphicsContext2D();
      
      //call the draw method
      draw(gc);
   }
   
   //create draw method
   public void draw(GraphicsContext gc) {
      
      //fill out the maze - scan the rows and collumns, and depending on if 0 or 1, set white or black
      for(int x = 0; x < maze.length; x++) {
         for(int y = 0; y < maze[0].length; y++) {
         
            //switch statement for if it is 0 or 1
            switch(maze[x][y]) {
               case 0:
                  gc.setFill(Color.WHITE);
                  break;
               case 1:
                  gc.setFill(Color.BLACK);
                  break;
            }
            
            //draw the rectangles, multiply by 25 because that is the length
            gc.fillRect(x*25, y*25, 25, 25);
         }
      }
      
      //goes through only the first row, if there is a 0 the cyan box appears
      for(int x = 0; x < 21; x ++) {
      
         //if that square is valid, then print the box in that position
         if(maze[x][0] == 0) {
            pX = x * 25;
         }
      }
            //create cyan box
            gc.setFill(Color.CYAN);
            gc.fillRect(pX, 0, 25, 25);
   }
   
   //create listener
   public class KeyListenerDown implements EventHandler<KeyEvent> {
      public void handle(KeyEvent ke) {
         
         //clear box to update position
         gc.clearRect(pX, pY, 25, 25);
                  
         //if user presses up, and it is a 0, then update position of the box
         if (ke.getCode() == KeyCode.UP) {
         
            //if statement to keep it in bounds (won't create error if you press up when you are at the top of the scene)
            if (pY > 0) {
               
               //check to see if that square you will move to is valid by subtracting from y variable
               if(maze[pX/25][(pY/25) - 1] == 0) {
               
                  pY = pY - 25;  //subtract 25 from position to move square up
               }
            }
         }
         
         //if user presses down, and it is a 0, then update position of the box
         else if (ke.getCode() == KeyCode.DOWN) {
         
            //only move if y position is less than 500 (won't create error if you press down when at the bottom of the scene)
            if (pY < 500) {
            
               //check to see if square you will move to is valid by adding to y variable
               if(maze[pX/25][(pY/25) + 1] == 0) {
               
                  pY = pY + 25;  //add 25 to position to move down
               }
            }
         }
         
         //if user presses left, and it is a 0, then update position of the box
         else if (ke.getCode() == KeyCode.LEFT) {
            
            //only move if x position is greater than 0 (won't create error if you press left while at left side of the scene)
            if (pX > 0) {
            
               //check to see if square you will move to is valid by subtracting from x variable
               if(maze[(pX/25) - 1][(pY/25)] == 0) {
               
                  pX = pX - 25;  //subtract 25 from position to move left
               }
            }
         }
         
         //if user presses right, and it is a 0, then update position of the box
         else if (ke.getCode() == KeyCode.RIGHT) {
         
            //only move if x position is less than 500 (won't create error if you are at right side of scene and press right)
            if (pX < 500) {
            
               //check to see if square is valid by adding to x position
               if(maze[(pX/25) + 1][(pY/25)] == 0) {
               
                  pX = pX + 25; //add 25 to move right
               }
            }
         }
         
         //if position is equal to 20 (bc arrays begin at 0) which is the bottom row         
         if ((pY/25) == 20) {
         
            //if that position is valid, then print out you win
            if(maze[pX/25][(pY/25)] == 0) {
               System.out.println("You Win!");
            }
         }
         
            //draw box so it is visible after moving
            gc.setFill(Color.CYAN);
            gc.fillRect(pX, pY, 25, 25);
                
     }
  }
         
}

