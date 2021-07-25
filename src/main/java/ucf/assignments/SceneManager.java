/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Glenroy Little
 */

package ucf.assignments;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    Map<String, Scene> scenes = new HashMap<>();

    void load() {
        //create item model
        ItemModel itemModel = new ItemModel();

        //instantiate controllers passing the model and Scene manager to it
        MainWindowController mainWindowController = new MainWindowController(itemModel, this);
        AddItemWindowController addItemWindowController = new AddItemWindowController(itemModel, this);

        //create parent root
        Parent root;

        //repeat for each controller
            //fxml load first window and controller into a loader
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        loader.setController(mainWindowController);
            //try loop
        try {
            //set root to loader.load();
            root = loader.load();
            //put scene and string name into scenes map
            scenes.put("MainWindow", new Scene(root));
            //catch ioexception
        } catch(IOException e) {
            e.printStackTrace();
        }

        //for the AddItemWindow
        loader = new FXMLLoader(getClass().getResource("AddItemWindow.fxml"));
        loader.setController(addItemWindowController);

        try {
            root = loader.load();
            scenes.put("AddItem", new Scene(root));
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public Scene getScene(String name) {
        return scenes.get(name);
    }
}
