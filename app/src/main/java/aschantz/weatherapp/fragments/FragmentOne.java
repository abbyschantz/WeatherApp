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

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import aschantz.weatherapp.R;
import aschantz.weatherapp.WeatherDetails;

/**
 * Created by aschantz on 11/27/16.
 * main weather info: current temperature, description, weather icon
 */
public class FragmentOne extends Fragment {

    private String date = "EEE, d MMM yyyy";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_one, null);
        WeatherDetails activity = (WeatherDetails) getActivity();
        String tempResult = "" + activity.getWeatherResult().getMain().getTemp();
        Double tempDouble = activity.getWeatherResult().getMain().getTemp();
        String desc = activity.getWeatherResult().getWeather().get(0).getDescription();
        Log.d("desc", desc);
        Integer dateResult = activity.getWeatherResult().getDt();

        TextView tvTemp = (TextView) rootView.findViewById(R.id.tvCurrentTempResult);
        tvTemp.setText(tempResult + "°");

        TextView descResult = (TextView) rootView.findViewById(R.id.descResult);
        descResult.setText(desc);

        if (tempDouble > 75.00) {
            tvTemp.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        String cityNameResult = activity.getWeatherResult().getName();
        TextView tvCityName = (TextView) rootView.findViewById(R.id.tvCityName);
        tvCityName.setText(cityNameResult);

        String dtConverted = "" + convertDate(dateResult, date);
        TextView dtResult = (TextView) rootView.findViewById(R.id.date);
        dtResult.setText(dtConverted);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageWeather);
        String iconID = ((WeatherDetails) getActivity()).getWeatherResult().getWeather().get(0).getIcon();
        Glide.with(this).load("http://openweathermap.org/img/w/" + iconID + ".png").into(imageView);

        return rootView;


    }

    private String convertDate(Integer timestamp, String dateType) {
        try {
            long timestampLong = Long.valueOf(timestamp) * 1000;
            DateFormat dateTime = new SimpleDateFormat(dateType);
            Date dateTimestamp = (new Date(timestampLong));
            return dateTime.format(dateTimestamp);
        } catch (Exception e) {
            return getString(R.string.na);
        }
    }

}
