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
public class CityRecyclerAdapter extends RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder> implements CityInterfaceTouchHelperAdapter{

    private List<Cities> cityList;
    private EditInferface editInterface;

    public CityRecyclerAdapter(EditInferface editInterface) {
        //for sugar

        this.editInterface = editInterface;

        cityList = Cities.listAll(Cities.class);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //use the layout inflator and return a new holder with the inflator view
        View cityRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_row, null, false);
        return new ViewHolder(cityRow);
    }

    //where we add the data to the rows
    //called by the system as many items Android wants to load in the memory
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvCity.setText(cityList.get(holder.getAdapterPosition()).getItemTitle());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // click...
//                Intent intent = new Intent();
//                intent.setClass(view.getContext(),WeatherDetails.class);
//                view.getContext().startActivity(intent);
//            }
//        });

        holder.tvCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(v.getContext(), WeatherDetails.class);
                String cityName = cityList.get(holder.getAdapterPosition()).getItemTitle();
                intent.putExtra("cityName", cityName);
                v.getContext().startActivity(intent);
//                editInterface.showEditDialog(
//                        cityList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
            }
        });

    }

    //returns how many items we have
    //if you leave this at 0, you will always have an empty list
    @Override
    public int getItemCount() {
        return cityList.size();
    }


    //remove item from object list and refresh recycler view with its location
    @Override
    public void onItemDismiss(int position) {
        //for sugar
        cityList.get(position).delete();

        cityList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

        //same as above but using add and remove
        cityList.add(toPosition, cityList.get(fromPosition));
        cityList.remove(fromPosition);

        notifyItemMoved(fromPosition, toPosition);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCity;


        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);


        }


    }

    public void addCity(Cities city){
        city.save(); //sugar
        cityList.add(0, city);


        notifyItemInserted(0);
    }

    public void deleteCity(Cities city) {
        city.delete();
        cityList.remove(city);
    }

    public void edit(Cities city, int position) {
        city.save();
        cityList.set(position, city);
        notifyItemChanged(position);
    }
}
