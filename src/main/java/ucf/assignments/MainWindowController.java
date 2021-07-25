package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class MainWindowController {

    @FXML
    private TableView<?> ItemsTableView;

    @FXML
    private TableColumn<?, ?> ItemsSerialNumberColumn;

    @FXML
    private TableColumn<?, ?> ItemsNameColumn;

    @FXML
    private TableColumn<?, ?> ItemsValueColumn;

    @FXML
    void addNewItemButtonClicked(ActionEvent event) {
        //string = field.gettext()
        //name string = field.getText()
        //value double = field.gettext()

        //Item item = createNewItem(string, name,value)

        //ItemModel.add(item);
    }

    public Item createNewItem(String sn, String name, double value) {
        return new Item(sn, name, value);
    }

    @FXML
    void deleteSelectedItemButtonClicked(ActionEvent event) {

    }

    @FXML
    void saveAsButtonClicked(ActionEvent event) {
        //filename = filechooser get name
        //filetype = filechooser get type

        //if CSV {saveAsCSV}
        //if HTML {saveAsHTML}
        //if JSON {saveAsJSON}
    }

    public void saveAsCSV(String filename) {
        //open up filename
        //for each item in the item model
        //write the item to file
    }

}
