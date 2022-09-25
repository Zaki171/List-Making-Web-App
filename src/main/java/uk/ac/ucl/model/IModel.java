package uk.ac.ucl.model;

import uk.ac.ucl.dataStructure.Item;

import java.io.IOException;
import java.util.ArrayList;

public interface IModel {//Any model that implements this interface can be used, allowing for different data structures and methods for handling data

    void readFile() throws IOException;

    boolean addList(String name);

    void delList(String name);

    boolean addItem(String listName, String text, String url, String img, String linkToList);

    boolean editItem(String listName, String oldText, String oldUrl, String oldImage, String oldLink, String newText, String newUrl, String newImage, String newLink);

    void delItem(String listName, String text, String url, String image, String linkToList);

    ArrayList<String> getListLabels();

    ArrayList<Item> getItemsInList(String listName);

    boolean renameList(String origLabel, String newName);

    ArrayList<String> searchForList(String name);

    ArrayList<Item> searchForItem(String listName, String userInput);//Item class required

    ArrayList<Item> getAllItems();

}
