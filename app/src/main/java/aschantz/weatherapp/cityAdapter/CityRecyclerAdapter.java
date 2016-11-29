package aschantz.weatherapp.cityAdapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import aschantz.weatherapp.CitiesList;
import aschantz.weatherapp.EditInferface;
import aschantz.weatherapp.MainActivity;
import aschantz.weatherapp.R;
import aschantz.weatherapp.WeatherDetails;
import aschantz.weatherapp.data.Cities;

/**
 * Created by aschantz on 11/27/16.
 */
public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder> implements CityInterfaceTouchHelperAdapter {


    private List<Cities> cityList;

    public CityRecyclerAdapter(EditInferface editInterface) {

        cityList = Cities.listAll(Cities.class);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cityRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_row, null, false);
        return new ViewHolder(cityRow);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvCity.setText(cityList.get(holder.getAdapterPosition()).getItemTitle());


        holder.tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), WeatherDetails.class);
                String cityName = cityList.get(holder.getAdapterPosition()).getItemTitle();
                intent.putExtra("cityName", cityName);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                deleteCity(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }


    @Override
    public void onItemDismiss(int position) {
        cityList.get(position).delete();

        cityList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

        cityList.add(toPosition, cityList.get(fromPosition));
        cityList.remove(fromPosition);

        notifyItemMoved(fromPosition, toPosition);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvCity;
        public Button btnDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);


        }


    }

    public void addCity(Cities city) {
        city.save();
        cityList.add(0, city);


        notifyItemInserted(0);
    }

    public void deleteCity(int index) {
        cityList.get(index).delete();
        cityList.remove(index);
        notifyDataSetChanged();
    }

    public void edit(Cities city, int position) {
        city.save();
        cityList.set(position, city);
        notifyItemChanged(position);
    }
}
