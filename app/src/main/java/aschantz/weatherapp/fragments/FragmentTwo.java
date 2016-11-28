package aschantz.weatherapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import aschantz.weatherapp.R;
import aschantz.weatherapp.WeatherDetails;

/**
 * Created by aschantz on 11/27/16.
 * detailed weather info: min and max temperature, humidity, sunrise and sunset, etc.
 */
public class FragmentTwo extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, null);
        WeatherDetails activity = (WeatherDetails) getActivity();
        String minTemp = "min temp: "+activity.getWeatherResult().getMain().getTempMin();
        String maxTemp = "max temp: "+activity.getWeatherResult().getMain().getTempMax();
        String currentTemp = "now temp: "+activity.getWeatherResult().getMain().getTemp();
        String humidity = "humidity: "+activity.getWeatherResult().getMain().getHumidity();
        String pressure = "pressure: "+activity.getWeatherResult().getMain().getPressure();
        String lon = "lon: "+activity.getWeatherResult().getCoord().getLon();
        String lat = "lat: "+activity.getWeatherResult().getCoord().getLat();
        Integer sunriseStamp = activity.getWeatherResult().getSys().getSunrise();
        Integer sunsetStamp = activity.getWeatherResult().getSys().getSunset();

        TextView minTempResult = (TextView) rootView.findViewById(R.id.tvMinTempResult);
        minTempResult.setText(minTemp);

        TextView maxTempResult = (TextView) rootView.findViewById(R.id.tvMaxTempResult);
        maxTempResult.setText(maxTemp);

        TextView humidityResult = (TextView) rootView.findViewById(R.id.tvHumidityResult);
        humidityResult.setText(humidity);

        String sunset = "sunset: "+convertDate(sunriseStamp);
        TextView sunsetResult = (TextView) rootView.findViewById(R.id.tvSunsetResult);
        sunsetResult.setText(sunset);

        String sunrise = "sunrise: "+convertDate(sunsetStamp);
        TextView sunriseResult = (TextView) rootView.findViewById(R.id.tvSunriseResult);
        sunriseResult.setText(sunrise);

        TextView pressureResult = (TextView) rootView.findViewById(R.id.tvPressureResult);
        pressureResult.setText(pressure);

        TextView lonResult = (TextView) rootView.findViewById(R.id.tvLonResult);
        lonResult.setText(lon);

        TextView latResult = (TextView) rootView.findViewById(R.id.tvLatResult);
        latResult.setText(lat);

        return rootView;
    }

    private String convertDate(Integer timestamp) {
        try {
            long timestampLong = Long.valueOf(timestamp)*1000;
            DateFormat dateTime = new SimpleDateFormat("hh:mm aaa");
            Date dateTimestamp = (new Date(timestampLong));
            return dateTime.format(dateTimestamp);
        }
        catch (Exception e){
            return "x";
        }
    }
}


