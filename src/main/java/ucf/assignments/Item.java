/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Glenroy Little
 */

package ucf.assignments;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    SimpleStringProperty name;
    SimpleStringProperty serialNumber;
    SimpleDoubleProperty value;
    public Item(String name, String serialNumber, Double value) {
        this.name = new SimpleStringProperty(name);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.value = new SimpleDoubleProperty(value);
    }
}
