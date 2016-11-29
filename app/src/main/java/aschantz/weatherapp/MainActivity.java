package aschantz.weatherapp;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import aschantz.weatherapp.adapter.MainPagerAdapter;
import aschantz.weatherapp.api.WeatherApi;
import aschantz.weatherapp.data.WeatherResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView tempTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button goToWeatherDetails = (Button) findViewById(R.id.goToWeatherDetails);
        goToWeatherDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeatherDetailsActivity(view);
            }
        });

        Button goToCitiesList = (Button) findViewById(R.id.goToCitiesList);
        goToCitiesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCitiesListActivity(view);
            }
        });
    }


    public void openWeatherDetailsActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, WeatherDetails.class);
        intent.putExtra("cityName","Budapest");
        startActivity(intent);
    }

    public void openCitiesListActivity(View view) {
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, CitiesList.class);
        startActivity(intent);
    }




    /////////////////////THIS IS WORKING //////////////////////
//    private TextView tvResult;
//    private String cityName = "Budapest";
//    private String units = "imperial";
//    private String appid = "301a264cbcf2c4b671a784dfb80413d9";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//        //create a retrofit object that reps one server communication
//        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://api.openweathermap.org").
//                addConverterFactory(GsonConverterFactory.create()).build();
//        final WeatherApi weatherApi = retrofit.create(WeatherApi.class);
//
//        tvResult = (TextView) findViewById(R.id.tvResult);
//        final EditText etMoney = (EditText) findViewById(R.id.etMoney);
//        Button btnGetRates = (Button) findViewById(R.id.btnGetRates);
//        btnGetRates.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Call<WeatherResult> call = weatherApi.getWeatherBudapest(cityName, units, appid);
//                call.enqueue(new Callback<WeatherResult>() {
//                    @Override
//                    public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
//                        tvResult.setText("" + response.body().getMain().getTemp());
//                    }
//
//                    @Override
//                    public void onFailure(Call<WeatherResult> call, Throwable t) {
//                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }

    ///////// END THIS IS WORKING ////////////
}

