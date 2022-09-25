package uk.ac.ucl.model;

import uk.ac.ucl.dataStructure.ItemList;
import uk.ac.ucl.dataStructure.ListCollection;
import uk.ac.ucl.dataStructure.Item;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Model implements IModel
{
  private final ListCollection lc = new ListCollection();

  public void readFile() throws IOException {
    Scanner sc = new Scanner(new File("./data/data.csv"));
    if(sc.hasNextLine()){
      String[] listLabels = sc.nextLine().split(",");//get all the list labels first
      for(String l : listLabels){
        addList(l);
      }
      while(sc.hasNextLine()){
        String[] row = sc.nextLine().split(",",-1);
        addItem(row[0],row[1],row[2],row[3],row[4]);
      }
    }
    sc.close();
  }

  public void updateFile(){
    try{
      loadData();
    }catch(IOException e){
      System.out.println("File could not be updated");
    }
  }

  public ArrayList<String> searchForList(String keyword)
  {
    ArrayList<String> results = new ArrayList<>();
    for(String l : lc.getLabels()){
      if(l.equals(keyword)){
        results.add(l);
      }
    }
    return results;
  }

  public boolean addList(String label){
    if(getListLabels().contains(label)){
      return false;
    }
    else{
      lc.addList(label);
      updateFile();
      return true;
    }
  }

  public ArrayList<String> getListLabels(){
    return lc.getLabels();
  }

  public boolean addItem(String listName, String itemText, String url, String image, String listLink){
    if(!(getListLabels().contains(listLink))&&!(listLink.equals(""))){
      return false;
    }
    else if(itemText.equals("") && url.equals("") && image.equals("") && listLink.equals("")){
      return false;
    }
    else{
      ItemList il = lc.getList(listName);
      il.addItem(itemText,url,image,listLink);
      updateFile();
      return true;
    }
  }

  public ArrayList<Item> getItemsInList(String listName){
    ItemList il = lc.getList(listName);
    return il.getAllItems();
  }

  public void delList(String label){
    ItemList il = lc.getList(label);
    lc.delList(il);
    for(Item i : il.getAllItems()){
      delFromDir(i.getFile());
    }
    for(Item i : getAllItems()){
      if(i.getLink().equals(label)){
        i.setLink("");
      }
    }
    checkEmptyItems();
    updateFile();
  }


  public boolean renameList(String origLabel, String newName) {
    if(getListLabels().contains(newName)){
      return false;
    }
    else{
      ItemList il = lc.getList(origLabel);
      il.setLabel(newName);
      updateLinks(origLabel,newName);
      updateFile();
      return true;
    }
  }

  private void updateLinks(String origLabel, String newLabel){
    for(Item i : getAllItems()){
      if(i.getLink().equals(origLabel)){
        i.setLink(newLabel);
      }
    }
    updateFile();
  }

  public void delItem(String id, String text, String url, String image, String linkToList) {
    ItemList il = lc.getList(id);
    Item i = il.getItem(text,url,image,linkToList);
    il.delItem(i);
    delFromDir(image);
    updateFile();
  }

  public boolean editItem(String oldId, String oldText, String oldUrl, String oldImage, String oldLink, String newText, String newUrl, String newImage, String newLink) {
    if(!(getListLabels().contains(newLink))&&!(newLink.equals(""))){
      return false;
    }
    else if(newText.equals("") && newUrl.equals("") && newImage.equals("") && newLink.equals("")){//no empty items allowed
      return false;
    }
    else{
      ItemList il = lc.getList(oldId);
      Item i = il.getItem(oldText,oldUrl,oldImage,oldLink);
      i.edit(newText,newUrl,newImage,newLink);
      delFromDir(oldImage);
      updateFile();
      return true;
    }
  }

  public ArrayList<Item> searchForItem(String id, String userInput) {
    ArrayList<Item> results = new ArrayList<>();
    ItemList il = lc.getList(id);
    for(Item i : il.getAllItems()){
      if(i.getFile().equals(userInput)||i.getLink().equals(userInput)||i.getText().equals(userInput)||i.getURL().equals(userInput))//any part of the item is compared to what the user entered
      {
        results.add(i);
      }
    }
    return results;
  }

  public ArrayList<Item> getAllItems() {//For viewing all the items
    ArrayList<Item> allItems = new ArrayList<>();
    for(String s : lc.getLabels()){
      ItemList il = lc.getList(s);
      for(Item i : il.getAllItems()){
        allItems.add(i);
      }
    }
    return allItems;
  }

  public void loadData() throws IOException {
    PrintWriter pw = new PrintWriter(new FileWriter("./data/data.csv"));
    ArrayList<String> labels = lc.getLabels();
    for(String l : labels){
      pw.write(l);
      pw.write(',');
    }
    for(String l : labels){
      ItemList il = lc.getList(l);
      for(Item i : il.getAllItems()){
        pw.write("\n");
        pw.write(il.getLabel());
        pw.write(',');
        pw.write(i.getText());
        pw.write(',');//commas separate values
        pw.write(i.getURL());
        pw.write(',');
        pw.write(i.getFile());
        pw.write(',');
        pw.write(i.getLink());
        pw.write(',');
      }
    }
    pw.close();

  }

  private void delFromDir(String image){//This is to ensure that we don't store unnecessary files.
    int totalNum = 0; // This will be the total number of images
    for(String l : lc.getLabels()){
      ItemList il = lc.getList(l);
      totalNum = totalNum + il.getItemsWithImg(image);
    }
    if(totalNum==0){
      try {
        File f = new File("src/main/webapp/files/"+image);
        if(f.exists()){
          if(!(f.delete())){
            System.out.println("Could not delete");
          }
        }
      }catch(Exception e){
        System.out.println("Deletion failed");
      }
    }
  }

  private void checkEmptyItems(){//Since items can be a link to another list, when that list is deleted we don't want to store an empty item
    for(String l : getListLabels()){
      ItemList il = lc.getList(l);
      ArrayList<Item> itemsToDel = new ArrayList<>();
      for(Item i : il.getAllItems()){
        if(i.getLink().equals("")&&i.getFile().equals("")&&i.getText().equals("")&&i.getURL().equals("")){
          itemsToDel.add(i);
        }
      }
      for(Item i : itemsToDel){
        il.delItem(i);
      }
    }
  }
}
