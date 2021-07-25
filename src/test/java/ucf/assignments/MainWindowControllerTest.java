/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Glenroy Little
 */

package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowControllerTest {

    @Test
    void can_edit_name_of_inventory_item() {
        Item test = new Item("Xbox One", "AXB124AXY3", 399.00);

        test.setName("test");
        assertEquals(test.getName(), "test");
    }

    @Test
    void can_add_new_inventory_item() {
        ItemModel model = new ItemModel();
        model.getItems().add(new Item("test", "AXB124AXY3", 399.00));

        assertEquals(model.get(0).getName(), "test");
    }

    @Test
    void can_remove_an_inventory_item() {

    }

    @Test
    void can_edit_existing_item_value() {
        Item item = new Item("Xbox One", "AXB124AXY3", 399.00);

        item.setValue(100);

        assertEquals(item.getValue(), 100.0);
    }

    @Test
    void can_edit_existing_item_serial_number() {
        Item item = new Item("Xbox One", "AXB124AXY3", 399.00);

        item.setSerialNumber("testtestte");

        assertEquals(item.getSerialNumber(), "testtestte");
    }

    @Test
    void can_edit_existing_item_name() {
        Item item = new Item("Xbox One", "AXB124AXY3", 399.00);

        item.setName("test");

        assertEquals(item.getName(), "test");
    }
}