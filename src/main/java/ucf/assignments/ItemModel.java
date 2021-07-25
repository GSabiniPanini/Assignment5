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

    public Item get(int index) {
        return items.get(index);
    }

    public boolean isValidSerialNumber(String s) {
        //flag bool
        boolean flag = true;

        //discern that serial number has 10 characters of either letter or digit
        if(hasCorrectChars(s)) {
        } else {
            flag = false;
        }

        //discern that serial number is unique
        if(flag) {
            flag = isUniqueSerialNumber(s);
        }

        //return bool flag
        return flag;
    }

    private boolean hasCorrectChars(String s) {
        int n = s.length();
        boolean flag = true;
        //convert string to char array
        char[] array = s.toCharArray();

        //discern sn has 10 characters of digits or letters
        if(n == 10)
        {
            for(int i = 0; i<n; i++) {
                if(Character.isLetter(array[i]) || Character.isDigit(array[i])) {
                }
                else {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }

        return flag;
    }

    private boolean isUniqueSerialNumber(String s) {
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
