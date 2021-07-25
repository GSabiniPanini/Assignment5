package ucf.assignments;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MainWindowController implements Initializable {

    @FXML private TableView<Item> itemsTableView;
    @FXML private TableColumn<Item, String> itemsSerialNumberColumn;
    @FXML private TableColumn<Item, String> itemsNameColumn;
    @FXML private TableColumn<Item, Double> itemsValueColumn;
    @FXML private Button deleteSelItemButton;

    @FXML
    void addNewItemButtonClicked(ActionEvent event) {
        //string = field.gettext()
        //name string = field.getText()
        //value double = field.gettext()

        //Item item = createNewItem(string, name,value)

        //ItemModel.add(item);
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

    @FXML
    void editNameText(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        int index = itemStringCellEditEvent.getTablePosition().getRow();
        itemStringCellEditEvent.getTableView().getItems().get(index).setName(itemStringCellEditEvent.getNewValue());

        itemsTableView.sort();
    }

    @FXML
    void editSerialNumberText(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        int index = itemStringCellEditEvent.getTablePosition().getRow();

        if(isValidSerialNumber(itemStringCellEditEvent.getNewValue())) {
            itemStringCellEditEvent.getTableView().getItems().get(index).setSerialNumber(itemStringCellEditEvent.getNewValue());
        } else {
            itemStringCellEditEvent.getTableView().getItems().get(index).setSerialNumber(itemStringCellEditEvent.getOldValue());
        }

        itemsTableView.sort();
    }

    private boolean isValidSerialNumber(String sn) {
        //necessary variables
        int n = sn.length();
        boolean flag = true;

        //convert string to char array
        char[] array = sn.toCharArray();

        //discern sn has 10 characters of digits or letters
        if(n == 10)
        {
            for(int i = 0; i<n; i++) {
                if(!Character.isLetter(array[i]) && !Character.isDigit(array[i])) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }

        //discern that serial number is unique
        if(flag) {
            if(model.isUniqueSerialNumber(sn) == false) {
                flag = false;
            }
        }

        //return bool flag
        return flag;
    }

    @FXML
    void editValueText(ActionEvent event) {

    }

    private ItemModel model;
    private SceneManager sceneManager;

    public MainWindowController(ItemModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up cellFactory for Table view
        itemsNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        itemsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        itemsSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        itemsSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        itemsValueColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));
        itemsValueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        //add sorting
        itemsTableView.getSortOrder().add(itemsNameColumn);
        itemsTableView.getSortOrder().add(itemsValueColumn);
        itemsTableView.getSortOrder().add(itemsSerialNumberColumn);

        //set initial Items
        try {
            model.getItems().add(new Item("Xbox One", "AXB124AXY3", 399.00));
            model.getItems().add(new Item("SamsungTV", "S40AZBDE47", 599.99));

            SortedList<Item> sortedItems = new SortedList<>(model.getItems());
            sortedItems.comparatorProperty().bind(itemsTableView.comparatorProperty());

            itemsTableView.setItems(sortedItems);
            itemsTableView.sort();
        } catch(IllegalArgumentException e) {
            //if list can not be found, leave table blank
            itemsTableView.setItems(null);
        }

    }

    public Item createNewItem(String sn, String name, double value) {
        return new Item(sn, name, value);
    }

    public void saveAsCSV(String filename) {
        //open up filename
        //for each item in the item model
        //write the item to file
    }
}