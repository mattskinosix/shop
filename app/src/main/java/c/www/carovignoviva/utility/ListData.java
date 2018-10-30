package c.www.carovignoviva.utility;

public class ListData {
    private static String image;
    private String name;
    private String visit;


    public ListData(String image,String name,String visit){
        this.image=image;
        this.name=name;
        this.visit=visit;
    }
    public  void setImage(String image){
        ListData.image =image; }
    public String getImage(){return this.image;}
    public void setName(String name) {this.name=name;}
    public String getName(){return name;}
    public void setVisit(String visit){this.visit=visit;}
    public String getVisit(){return visit;}

}
