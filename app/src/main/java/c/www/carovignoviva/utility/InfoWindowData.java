package c.www.carovignoviva.utility;

import java.io.Serializable;

public class InfoWindowData implements Serializable {
    private String image;
    private String description;
    private String details;
    private String transport;
    private String title;



    public  String getTitle(){
        return title;
    }


    public void setTitle(String title){
        this.title=title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String food) {
        this.details = food;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }
}