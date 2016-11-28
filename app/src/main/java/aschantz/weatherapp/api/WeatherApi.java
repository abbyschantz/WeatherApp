package aschantz.weatherapp.api;

import aschantz.weatherapp.data.WeatherResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aschantz on 11/23/16.
 */
public interface WeatherApi {

    @GET("data/2.5/weather")
    Call<WeatherResult> getWeatherBudapest(@Query("q") String base,
                                           @Query("units") String units,
                                           @Query("appid") String appid);
}
