package aschantz.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import aschantz.weatherapp.cityAdapter.CityRecyclerAdapter;
import aschantz.weatherapp.cityAdapter.CityTouchHelperCallback;
import aschantz.weatherapp.data.Cities;

/**
 * Created by aschantz on 11/27/16.
 */
public class CitiesList extends AppCompatActivity implements EditInferface {

    public static final int REQUEST_CODE_ADD = 100;
    public static final String KEY_CITY_TO_EDIT = "KEY_CITY_TO_EDIT";
    public static final int REQUEST_CODE_EDIT = 101;
    private CityRecyclerAdapter cityRecyclerAdapter;
    private RecyclerView recyclerCity;
    private int positionToEdit = -1;
    private Long idToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_list);

        setupUI();
    }

    private void setupUI() {
        setupToolbar();

        setupFloatingActionButton();

        setupRecyclerView();

        setupAddItem();

    }

    private void setupRecyclerView() {
        recyclerCity = (RecyclerView) findViewById(
                R.id.recyclerCity);
        recyclerCity.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);
        recyclerCity.setLayoutManager(mLayoutManager);

        cityRecyclerAdapter = new CityRecyclerAdapter(this);

        ItemTouchHelper.Callback callback = new CityTouchHelperCallback(cityRecyclerAdapter);
        ItemTouchHelper touchHelper= new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerCity);

        recyclerCity.setAdapter(cityRecyclerAdapter);
    }

    private void setupFloatingActionButton() {

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddCity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShowAdd = new Intent();
                intentShowAdd.setClass(CitiesList.this, AddCityActivity.class);
                startActivityForResult(intentShowAdd, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD) {
                Cities newCity = (Cities) data.getSerializableExtra(
                        AddCityActivity.KEY_CITY);

                cityRecyclerAdapter.addCity(newCity);
                recyclerCity.scrollToPosition(0);
            } else if (requestCode == REQUEST_CODE_EDIT) {

                Cities changedCity = (Cities) data.getSerializableExtra(
                        AddCityActivity.KEY_CITY);
                changedCity.setId(idToEdit);

                cityRecyclerAdapter.edit(changedCity, positionToEdit);


            }
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupAddItem() {
        final EditText etCity = (EditText) findViewById(R.id.etCity);
        Button btnSaveItem = (Button) findViewById(R.id.btnAdd);


        btnSaveItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityRecyclerAdapter.addCity(new Cities(etCity.getText().toString()));
                recyclerCity.scrollToPosition(0);
            }
        });
    }


    @Override
    public void showEditDialog(Cities cityToEdit, int position) {
        positionToEdit = position;
        idToEdit = cityToEdit.getId();
        Intent intentShowEdit = new Intent();
        intentShowEdit.setClass(CitiesList.this, AddCityActivity.class);
        intentShowEdit.putExtra(KEY_CITY_TO_EDIT,cityToEdit);
        startActivityForResult(intentShowEdit, REQUEST_CODE_EDIT);
    }


}
