 /*
  * UCF COP3330 Summer 2021 Assignment 5 Solution
  * Copyright 2021 Glenroy Little
  */

 package ucf.assignments;

 import javafx.application.Application;
 import javafx.stage.Stage;

 public class InventoryTracker extends Application {

     public static void main(String[] args) {
         launch(args);
     }

     @Override
     public void start(Stage primaryStage) {
         SceneManager sceneManager = new SceneManager();
         sceneManager.load();

         primaryStage.setScene(sceneManager.getScene("MainWindow"));
         primaryStage.setTitle("Inventory Tracker");
         primaryStage.show();
     }

 }
