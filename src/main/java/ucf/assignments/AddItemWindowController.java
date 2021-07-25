package ucf.assignments;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddItemWindowController {

    @FXML private TextField valueField;
    @FXML private TextField serialNumberField;
    @FXML private TextField nameField;
    @FXML private Label errorLabel;

    private ItemModel model;
    private SceneManager sceneManager;

    public AddItemWindowController(ItemModel model, SceneManager sceneManager) {
        this.model = model;
        this.sceneManager = sceneManager;
    }

    @FXML
    void cancelClicked(ActionEvent event) {
        clearFields();
        Stage stage = (Stage) valueField.getScene().getWindow();

        stage.close();
    }

    @FXML
    void confirmClicked(ActionEvent event) {

        if(!nameField.getText().isEmpty() || !serialNumberField.getText().isEmpty() || !valueField.getText().isEmpty()) {
            String name = nameField.getText();
            String serialNumber = serialNumberField.getText();
            Double value = Double.valueOf(valueField.getText());
            boolean passed = false;

            if(value >= 0) {
                if(model.enoughChars(serialNumber)) {
                    if(model.isUnique(serialNumber)) {
                        if (name.length() >= 2 && name.length() <= 256) {
                            Item item = new Item(name, serialNumber, value);
                            model.getItems().add(item);

                            passed = true;
                        } else {
                            errorLabel.setText("name should be between 2 and 256 characters in length (inclusive)");
                        }
                    } else {
                        errorLabel.setText("That Serial Number already exists");
                    }
                } else {
                    errorLabel.setText("Serial Number should be 10 characters and will consist of letters and/or digits");
                }
            } else {
                errorLabel.setText("Value should be greater than or equal to 0");
            }

            if(passed) {
                errorLabel.setText("");
                Stage stage = (Stage) errorLabel.getScene().getWindow();
                stage.close();
            }
        } else {
            errorLabel.setText("Must fill fields");
        }
    }

    private void clearFields() {
        valueField.clear();
        serialNumberField.clear();
        nameField.clear();

        errorLabel.setText("");
    }

}
