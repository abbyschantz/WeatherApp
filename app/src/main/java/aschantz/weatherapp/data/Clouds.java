package aschantz.weatherapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aschantz on 11/27/16.
 */
public class Clouds {

    @SerializedName("all")
    @Expose
    public Integer all;


    //GETTER
    public Integer getAll() {
        return all;
    }
}
