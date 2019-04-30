package c.www.carovignoviva.utility;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class InfoWindowData implements Serializable {
    private String image;
    private String description;
    private String details;
    private String transport;
    private String title;
    private double Longitude,latitude;


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

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitudeLatitude(LatLng mat) {
        Longitude = mat.longitude;
        this.latitude = mat.latitude;
    }

    public double getLatitude() {
        return latitude;
    }

}