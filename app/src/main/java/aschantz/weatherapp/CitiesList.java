package aschantz.weatherapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.List;

import aschantz.weatherapp.cityAdapter.CityRecyclerAdapter;
import aschantz.weatherapp.cityAdapter.CityTouchHelperCallback;
import aschantz.weatherapp.data.Cities;

/**
 * Created by aschantz on 11/27/16.
 */
public class CitiesList extends AppCompatActivity implements EditInferface {

    public static final int REQUEST_NEW_ITEM = 101;
    public static final int REQUEST_CODE_ADD = 100;
    public static final String KEY_CITY_TO_EDIT = "KEY_CITY_TO_EDIT";
    public static final int REQUEST_CODE_EDIT = 102;
    private CityRecyclerAdapter cityRecyclerAdapter;
    private RecyclerView recyclerCity;
    private int positionToEdit = -1;
    private Long idToEdit = null;

    private DrawerLayout drawerLayout;
    private CoordinatorLayout layoutContent;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities_list);

        setupUI();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.


        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.action_add:
                                showCreateItemActivity();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;
                            case R.id.action_about:
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Toast.makeText(CitiesList.this, getString(R.string.txt_about), Toast.LENGTH_LONG).show();
                                break;
                            case R.id.action_help:
                                //showSnackBarMessage(getString(R.string.txt_help));
                                drawerLayout.closeDrawer(GravityCompat.START);
                                break;

                        }
                        return false;
                    }

                });
        setUpToolBar();


    }

    private void setUpToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.nav);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

    }

    private void showCreateItemActivity() {
        Intent intentStart = new Intent(CitiesList.this,
                AddCityActivity.class);
        startActivityForResult(intentStart, REQUEST_CODE_ADD);
    }

    private void setupUI() {

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
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
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
        intentShowEdit.putExtra(KEY_CITY_TO_EDIT, cityToEdit);
        startActivityForResult(intentShowEdit, REQUEST_CODE_EDIT);
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CitiesList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://aschantz.weatherapp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CitiesList Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://aschantz.weatherapp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


}
