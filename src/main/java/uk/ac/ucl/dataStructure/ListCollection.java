package uk.ac.ucl.dataStructure;
import java.util.ArrayList;


public class ListCollection {
    private ArrayList<ItemList> lists = new ArrayList<>();

    public void addList(String name){
        ItemList il = new ItemList(name);
        this.lists.add(il);
    }

    public ArrayList<String> getLabels(){
        ArrayList<String> labels = new ArrayList<>();
        for (ItemList list : lists){
            String label = list.getLabel();
            labels.add(label);
        }
        return labels;

    }

    public ItemList getList(String label){
        for (ItemList l: lists){
            if (l.getLabel().equals(label)){
                return l;
            }
        }
        return null;
    }

    public void delList(ItemList il) {
        lists.remove(il);
    }

}
