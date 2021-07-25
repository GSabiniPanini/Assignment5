package ucf.assignments;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.DoubleStringConverter;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML private ChoiceBox<String> choiceBox;
    @FXML private TextField searchQueryField;
    @FXML private TableView<Item> itemsTableView;
    @FXML private TableColumn<Item, Double> itemsValueColumn;
    @FXML private TableColumn<Item, String> itemsSerialNumberColumn;
    @FXML private TableColumn<Item, String> itemsNameColumn;

    private ItemModel model;
    private SceneManager sceneManager;
    private FileManager fileManager;

    public MainWindowController(ItemModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;
        this.fileManager = new FileManager();
    }

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
        if(itemsTableView.getFocusModel().getFocusedCell() != null) {
            int index = itemsTableView.getFocusModel().getFocusedIndex();
            removeItem(index);
        }

        itemsTableView.sort();
    }

    private void removeItem(int index) {
        model.getItems().remove(index);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up cellFactory for Table view
        itemsValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        itemsValueColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        itemsSerialNumberColumn.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        itemsSerialNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        itemsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        itemsNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //add sorting
        itemsTableView.getSortOrder().add(itemsNameColumn);
        itemsTableView.getSortOrder().add(itemsSerialNumberColumn);
        itemsTableView.getSortOrder().add(itemsValueColumn);

        //set initial Items
        try {
            model.getItems().add(new Item("Xbox One", "AXB124AXY3", 399.00));
            model.getItems().add(new Item("SamsungTV", "S40AZBDE47", 599.99));
            searchQueryField.setText("");

            FilteredList<Item> filteredList = new FilteredList(model.getItems());
            SortedList<Item> sortedItems = new SortedList<>(filteredList);

            sortedItems.comparatorProperty().bind(itemsTableView.comparatorProperty());

            //adding items to choiceBox
            choiceBox.getItems().addAll("Serial Number", "Name");
            choiceBox.setValue("Serial Number");

            //life filtering off of the search query
            searchQueryField.textProperty().addListener((obs,oldValue,newValue) -> {
                switch(choiceBox.getValue())
                {
                    case "Serial Number":
                        filteredList.setPredicate(p -> p.getSerialNumber().contains(newValue));
                        break;
                    case "Name":
                        filteredList.setPredicate(p -> p.getName().contains(newValue));
                        break;
                }
            });

            choiceBox.getSelectionModel().selectedItemProperty().addListener((obs,oldValue,newValue) -> {
                //reset table with new choice selected
                if(newValue != null) {
                    searchQueryField.setText("");
                }
            });

            itemsTableView.setItems(sortedItems);
            itemsTableView.sort();

        } catch(IllegalArgumentException e) {
            //if list can not be found, leave table blank
            itemsTableView.setItems(null);
        }


    }

    @FXML
    void saveAsButtonClicked(ActionEvent event) {
        Stage mainStage = (Stage) choiceBox.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("TSV file", "*.txt"),
                new ExtensionFilter("HTML file", "*.html"),
                new ExtensionFilter("JSON file", ".json"));
        File selectedFile = fileChooser.showSaveDialog(mainStage);

        if(selectedFile != null) {
            switch(fileChooser.getSelectedExtensionFilter().getDescription()) {
                case "TSV file":
                    fileManager.saveAsTSV(model, selectedFile);
                    break;
                case "HTML file":
                    fileManager.saveAsHTML(model, selectedFile);
                    break;
                case "JSON file":
                    fileManager.saveAsJSON(model, selectedFile);
                    break;
            }
        }
    }

    @FXML
    void loadButtonClicked(ActionEvent event) {
        Stage mainStage = (Stage) choiceBox.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("TSV files", "*.txt"),
                new ExtensionFilter("HTML files", "*.html"),
                new ExtensionFilter("JSON files", ".json"));
        File selectedFile = fileChooser.showOpenDialog(mainStage);

        if(selectedFile != null) {
            switch(fileChooser.getSelectedExtensionFilter().getDescription()) {
                case "TSV files":
                    fileManager.loadTSV(model, selectedFile);
                    itemsTableView.sort();
                    break;
                case "HTML files":
                    fileManager.loadHTML(model, selectedFile);
                    itemsTableView.sort();
                    break;
                case "JSON files":
                    fileManager.loadJSON(model, selectedFile);
                    itemsTableView.sort();
                    break;
            }
        }
        itemsTableView.sort();
    }
}
