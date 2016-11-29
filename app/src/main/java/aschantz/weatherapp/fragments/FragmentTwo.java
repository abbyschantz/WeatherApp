package aschantz.weatherapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

    private String timeAMPM = "hh:mm aaa";
    private String date = "EEE, d MMM yyyy";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, null);
        WeatherDetails activity = (WeatherDetails) getActivity();
        String minTemp = ""+activity.getWeatherResult().getMain().getTempMin()+"°";
        String maxTemp = ""+activity.getWeatherResult().getMain().getTempMax()+"°";
        String currentTemp = ""+activity.getWeatherResult().getMain().getTemp()+"°";

        String humidity = ""+activity.getWeatherResult().getMain().getHumidity()+"%";
        String pressure = getString(R.string.pressure)+activity.getWeatherResult().getMain().getPressure();
        String lon = getString(R.string.lon)+activity.getWeatherResult().getCoord().getLon();
        String lat = getString(R.string.lat)+activity.getWeatherResult().getCoord().getLat();
        String wind = ""+activity.getWeatherResult().getWind().getSpeed();

        Integer sunriseStamp = activity.getWeatherResult().getSys().getSunrise();
        Integer sunsetStamp = activity.getWeatherResult().getSys().getSunset();
        Integer dateResult = activity.getWeatherResult().getDt();

        TextView minTempResult = (TextView) rootView.findViewById(R.id.tvMinTempResult);
        minTempResult.setText(minTemp);

        TextView currentTempResult = (TextView) rootView.findViewById(R.id.tvCurrentTempResult);
        currentTempResult.setText(currentTemp);

        TextView maxTempResult = (TextView) rootView.findViewById(R.id.tvMaxTempResult);
        maxTempResult.setText(maxTemp);

        TextView humidityResult = (TextView) rootView.findViewById(R.id.tvHumidityResult);
        humidityResult.setText(humidity);

        String dtConverted = ""+convertDate(dateResult, date);
        TextView dtResult = (TextView) rootView.findViewById(R.id.tvDt);
        dtResult.setText(dtConverted);

        String sunset = ""+convertDate(sunriseStamp, timeAMPM);
        TextView sunsetResult = (TextView) rootView.findViewById(R.id.tvSunriseResult);
        sunsetResult.setText(sunset);

        String sunrise = ""+convertDate(sunsetStamp, timeAMPM);
        TextView sunriseResult = (TextView) rootView.findViewById(R.id.tvSunsetResult);
        sunriseResult.setText(sunrise);

        TextView windResult = (TextView) rootView.findViewById(R.id.tvWindResult);
        windResult.setText(wind);

        //TextView lonResult = (TextView) rootView.findViewById(R.id.tvLonResult);
        //lonResult.setText(lon);

        //TextView latResult = (TextView) rootView.findViewById(R.id.tvLatResult);
        //latResult.setText(lat);

        String cityNameResult = activity.getWeatherResult().getName();
        TextView tvCityName = (TextView) rootView.findViewById(R.id.tvCityName);
        tvCityName.setText(cityNameResult);


        return rootView;
    }

    private String convertDate(Integer timestamp, String dateType) {
        try {
            long timestampLong = Long.valueOf(timestamp)*1000;
            DateFormat dateTime = new SimpleDateFormat(dateType);
            Date dateTimestamp = (new Date(timestampLong));
            return dateTime.format(dateTimestamp);
        }
        catch (Exception e){
            return getString(R.string.na);
        }
    }
}


