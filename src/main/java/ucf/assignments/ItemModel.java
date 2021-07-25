package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ItemModel {
    public ObservableList<Item> items;

    public ItemModel() {
        this.items = FXCollections.observableArrayList();
    }

    public ItemModel(ObservableList<Item> items) {
        this.items = items;
    }

    public ObservableList<Item> getItems() {
        return items;
    }

    public boolean isUniqueSerialNumber(String s) {
        Set<String> unique = new HashSet<>();
        ArrayList<String> temp = new ArrayList<>();
        boolean flag = true;


        for(int i = 0; i<items.size(); i++) {
            temp.add(items.get(i).getSerialNumber());
        }

        for(String string : temp) {
            unique.add(string);
        }

        if(unique.add(s) == false) {
            flag = false;
        }

        return flag;
    }

}
