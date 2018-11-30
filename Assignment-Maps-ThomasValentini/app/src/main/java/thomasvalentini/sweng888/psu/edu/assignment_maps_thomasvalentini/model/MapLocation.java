package thomasvalentini.sweng888.psu.edu.assignment_maps_thomasvalentini.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class MapLocation implements Serializable {

    private String title;
    private String description;
    private String latitude;
    private String longitude;

    public MapLocation() {
    }

    public MapLocation(String title, String description, String latitude, String longitude) {
        this.title = title;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MapLocation(String title, String latitude, String longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LatLng getLatLang(){return new LatLng(Double.valueOf(this.latitude),Double.valueOf(this.longitude));}

    public String toString(){
        return String.format("MapLocation Object: title=%s, description=%s, latitude=%s, longitude=%s",title,description,latitude,longitude);
    }
}
