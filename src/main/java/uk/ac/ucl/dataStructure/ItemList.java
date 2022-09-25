package uk.ac.ucl.dataStructure;

import java.util.ArrayList;

public class ItemList {
    private String label;
    private ArrayList<Item> items = new ArrayList<Item>();

    public ItemList(String label){
        this.label = label;
    }

    public String getLabel(){
        return this.label;
    }

    public void addItem(String text, String url, String image, String link){
        Item item = new Item(text,url,image,link);
        this.items.add(item);
    }

    public ArrayList<Item> getAllItems(){
        return this.items;
    }

    public void setLabel(String newName){
        this.label = newName;
    }

    public Item getItem(String text, String url, String image, String linkToList){
        Item item = null;
        for(Item i : items){
            if(i.getLink().equals(linkToList)&&i.getURL().equals(url)&&i.getText().equals(text)&&i.getFile().equals(image)){
                item = i;
            }
        }
        return item;
    }

    public void delItem(Item i) {
        this.items.remove(i);
    }

    public int getItemsWithImg(String imgName){
        int num = 0;
        for(Item i : items){
            if(i.getFile().equals(imgName)){
                num++;
            }
        }
        return num;
    }
}

