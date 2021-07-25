package ucf.assignments;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MainWindowController implements Initializable {

    @FXML private TableView<Item> itemsTableView;
    @FXML private TableColumn<Item, Double> itemsValueColumn;
    @FXML private TableColumn<Item, String> itemsSerialNumberColumn;
    @FXML private TableColumn<Item, String> itemsNameColumn;
    @FXML private Button deleteSelItemButton;

    private ItemModel model;
    private SceneManager sceneManager;

    @FXML
    void addNewItemButtonClicked(ActionEvent event) {
        //make stage and show the stage
        Stage stage = new Stage();
        Scene scene = sceneManager.getScene("AddItem");

        stage.setTitle("Add an Item");
        stage.setScene(scene);
        stage.showAndWait();

        itemsTableView.sort();
    }

    @FXML
    void deleteSelectedItemButtonClicked(ActionEvent event) {
        if(itemsTableView.isFocused()) {
            int index = itemsTableView.getSelectionModel().getSelectedIndex();
            removeItem(index);
        }

        itemsTableView.sort();
    }

    private void removeItem(int index) {
        model.getItems().remove(index);
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

        editName(itemStringCellEditEvent.getTableView().getItems().get(index), itemStringCellEditEvent.getNewValue());

        itemsTableView.refresh();
        itemsTableView.sort();
    }

    private void editName(Item item, String newValue) {
        item.setName(newValue);
    }

    @FXML
    void editSerialNumberText(TableColumn.CellEditEvent<Item, String> itemStringCellEditEvent) {
        int index = itemStringCellEditEvent.getTablePosition().getRow();

        if(model.isValidSerialNumber(itemStringCellEditEvent.getNewValue())) {
            editSN(itemStringCellEditEvent.getTableView().getItems().get(index), itemStringCellEditEvent.getNewValue());
        } else {
            editSN(itemStringCellEditEvent.getTableView().getItems().get(index), itemStringCellEditEvent.getOldValue());
        }

        itemsTableView.refresh();
        itemsTableView.sort();
    }

    private void editSN(Item item, String newValue) {
        item.setSerialNumber(newValue);
    }

    @FXML
    void editValueText(TableColumn.CellEditEvent<Item, Double> itemStringCellEditEvent) {
        int index = itemStringCellEditEvent.getTablePosition().getRow();

        editValue(itemStringCellEditEvent.getTableView().getItems().get(index), itemStringCellEditEvent.getNewValue());

        itemsTableView.refresh();
        itemsTableView.sort();
    }

    private void editValue(Item item, Double newValue) {
        item.setValue(newValue);
    }

    public MainWindowController(ItemModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up cellFactory for Table view
        itemsValueColumn.setCellValueFactory(new PropertyValueFactory<Item, Double>("value"));
        itemsValueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        itemsSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("serialNumber"));
        itemsSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        itemsNameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        itemsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //add sorting
        itemsTableView.getSortOrder().add(itemsNameColumn);
        itemsTableView.getSortOrder().add(itemsSerialNumberColumn);
        itemsTableView.getSortOrder().add(itemsValueColumn);

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

    public void saveAsCSV(String filename) {
        //open up filename
        //for each item in the item model
        //write the item to file
    }
}
