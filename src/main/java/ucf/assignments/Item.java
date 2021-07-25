/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Glenroy Little
 */

package ucf.assignments;

import com.sun.javafx.binding.StringFormatter;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    SimpleDoubleProperty value;
    SimpleStringProperty serialNumber;
    SimpleStringProperty name;

    public Item(String name, String serialNumber, Double value) {
        this.name = new SimpleStringProperty(name);
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.value = new SimpleDoubleProperty(value);
    }

    public Item(String serialNumber) {
        this.name = new SimpleStringProperty("name");
        this.serialNumber = new SimpleStringProperty(serialNumber);
        this.value = new SimpleDoubleProperty(0.00);
    }

    public void setName(String name) {
        if(isValidName(name)) {
            this.name.set(name);
        }
    }

    private boolean isValidName(String name) {
        boolean flag = false;
        if(name.length() >= 2 && name.length() <= 256) {
            flag = true;
        }

        return flag;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber.set(serialNumber);
    }

    public void setValue(double value) {
        if(value >= 0.00) {
            this.value.set(value);
        }
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSerialNumber() {
        return serialNumber.get();
    }

    public SimpleStringProperty serialNumberProperty() {
        return serialNumber;
    }

    public double getValue() {
        return value.get();
    }

    public SimpleDoubleProperty valueProperty() {
        return value;
    }

    public String toTSVLine() {
        return String.format("%10.2f\t%s\t%s", getValue(), getSerialNumber(), getName());
    }

    public String toHTMLLine() {
        return String.format("%10.2f\t%s\t%s<br>", getValue(), getSerialNumber(), getName());
    }
}
