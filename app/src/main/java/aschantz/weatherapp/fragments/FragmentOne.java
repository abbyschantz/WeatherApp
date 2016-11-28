package aschantz.weatherapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import aschantz.weatherapp.R;
import aschantz.weatherapp.WeatherDetails;

/**
 * Created by aschantz on 11/27/16.
 * main weather info: current temperature, description, weather icon
 */
public class FragmentOne extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_one, null);
        WeatherDetails activity = (WeatherDetails) getActivity();
        String tempResult = ""+activity.getWeatherResult().getMain().getTemp();
        Double tempDouble = activity.getWeatherResult().getMain().getTemp();
        TextView tvTemp = (TextView) rootView.findViewById(R.id.tvCurrentTempResult);
        tvTemp.setText(tempResult);
        if (tempDouble > 75.00) {
            tvTemp.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        String cityNameResult = activity.getWeatherResult().getName();
        TextView tvCityName = (TextView) rootView.findViewById(R.id.tvCityName);
        tvCityName.setText(cityNameResult);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.imageWeather);
        String iconID = ((WeatherDetails)getActivity()).getWeatherResult().getWeather().get(0).getIcon();
        Glide.with(this).load("http://openweathermap.org/img/w/"+ iconID + ".png").into(imageView);

        return rootView;


    }

}
