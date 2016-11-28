package aschantz.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aschantz on 11/27/16.
 */
public class Coord {

    @SerializedName("lon")
    @Expose
    public Double lon;
    @SerializedName("lat")
    @Expose
    public Double lat;

    //getter


    public Double getLon() {
        return lon;
    }

    public Double getLat() {
        return lat;
    }
}
