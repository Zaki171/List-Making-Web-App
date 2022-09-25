package uk.ac.ucl.dataStructure;

public class Item {
    private String text;
    private String url;
    private String file;
    private String listLink;
    public Item(String text, String url, String image, String listLink){
        this.text = text;
        this.url = url;
        this.file = image;
        this.listLink = listLink;
    }

    public String getText(){
        return this.text;
    }

    public String getURL(){ return this.url;}

    public String getFile(){ return this.file;}

    public String getLink(){ return this.listLink; }

    public void edit(String text, String url, String file, String listLink){
        this.text=text;
        this.url=url;
        this.file=file;
        this.listLink=listLink;
    }

    public void setLink(String newLink){
        this.listLink = newLink;
    }

}
