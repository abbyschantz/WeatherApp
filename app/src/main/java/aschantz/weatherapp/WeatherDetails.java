package aschantz.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aschantz.weatherapp.adapter.MainPagerAdapter;
import aschantz.weatherapp.api.WeatherApi;
import aschantz.weatherapp.data.WeatherResult;
import aschantz.weatherapp.fragments.FragmentOne;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aschantz on 11/27/16.
 */
public class WeatherDetails extends AppCompatActivity {

    private String cityName = "Budapest";
    private String units = "imperial";
    private String appid = "301a264cbcf2c4b671a784dfb80413d9";
    public String tvResult = "test";


    private String myString = "hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_details);

        Bundle getName = getIntent().getExtras();
        cityName = getName.getString("cityName");


        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").
                addConverterFactory(GsonConverterFactory.create()).build();
        final WeatherApi weatherApi = retrofit.create(WeatherApi.class);


        Call<WeatherResult> call = weatherApi.getWeatherBudapest(cityName, units, appid);
        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                tvResult = "Budapest";

                weatherResult = response.body();

                ViewPager pager = (ViewPager) findViewById(R.id.pager);
                pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Toast.makeText(WeatherDetails.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("FAILURE", tvResult);
            }

        });


    }


    private WeatherResult weatherResult = null;

    public WeatherResult getWeatherResult() {
        return weatherResult;
    }

}
