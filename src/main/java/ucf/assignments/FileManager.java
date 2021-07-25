/*
 * UCF COP3330 Summer 2021 Assignment 5 Solution
 * Copyright 2021 Glenroy Little
 */

package ucf.assignments;

import java.io.*;
import java.util.Scanner;

public class FileManager {

    public void saveAsTSV(ItemModel model, File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(String.format("%10s%15s%9s", "Value", "Serial Number", "Name"));
            for(Item item : model.getItems()) {
                writer.println(item.toTSVLine());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTSV(ItemModel model, File file) {
        Double value;
        String temp;
        String serialNumber;
        String name;

        try {
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\t");

            model.getItems().clear();
            //clear the Header Line
            temp = sc.nextLine();
            while(sc.hasNextLine()) {
                temp = sc.next();
                value = Double.valueOf(temp.replace("$", ""));
                serialNumber = sc.next();
                name = sc.nextLine();

                model.getItems().add(new Item(name, serialNumber, value));
            }
            sc.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveAsHTML(ItemModel model, File file) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println("<!DOCTYPE html><html><body><table style=\"width:100%\"><tr><th>Value</th><th>Serial Number</th><th>Name</th></tr>");
            for(Item item : model.getItems()) {
                writer.println(item.toHTMLLine());
            }
            writer.println("</table></body></html>");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadHTML(ItemModel model, File file) {
        Double value;
        String temp;
        String serialNumber;
        String name;

        try {
            Scanner sc = new Scanner(file);

            model.getItems().clear();
            String garbage = sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if(line.contains("<tr><td>")) {
                    //sc.useDelimiter("\t");
                    String[] lineArray = line.split("</td><td>");
                    value = Double.valueOf(lineArray[0].replace("<tr><td>$", ""));
                    serialNumber = lineArray[1];
                    name = lineArray[2].replace("</td></tr>", "");

                    //temp = sc.next();
                    //value = Double.valueOf(temp);
                    //serialNumber = sc.next();

                    //String tempName = sc.nextLine();
                    //name = tempName.replace("<br>", "");

                    model.getItems().add(new Item(name, serialNumber, value));
                } else {
                    break;
                }
            }

            sc.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveAsJSON(ItemModel model, File file) {

    }

    public void loadJSON(ItemModel model, File file) {

    }
}
