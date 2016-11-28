package aschantz.weatherapp.data;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by aschantz on 11/27/16.
 */
public class Cities extends SugarRecord implements Serializable {

    private String cityName;

    //empty constructor
    public Cities() {

    }

    //command n, constructor, then select all.
    public Cities(String itemTitle) {
        this.cityName = itemTitle;
    }

    //command n and then getters and settlers. Select all.
    public String getItemTitle() {
        return cityName;
    }

    public void setItemTitle(String itemTitle) {
        this.cityName = itemTitle;
    }


}
