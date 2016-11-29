package aschantz.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import aschantz.weatherapp.data.Cities;

/**
 * Created by aschantz on 11/27/16.
 */
public class AddCityActivity extends AppCompatActivity {

    public static final String KEY_CITY = "KEY_CITIES";

    private Cities cityToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        final EditText etCity = (EditText) findViewById(R.id.etCity);

        if (getIntent() != null
                && getIntent().hasExtra(CitiesList.KEY_CITY_TO_EDIT)) {
            cityToEdit = (Cities) getIntent().getSerializableExtra(CitiesList.KEY_CITY_TO_EDIT);
            etCity.setText(cityToEdit.getItemTitle());

        }


        Button btnSave = (Button) findViewById(R.id.btnAddCity);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();

                Cities newCity = cityToEdit;
                if (newCity == null) {
                    newCity = new Cities(etCity.getText().toString());
                } else {
                    newCity.setItemTitle(etCity.getText().toString());
                }


                result.putExtra(KEY_CITY, newCity);

                setResult(RESULT_OK, result);
                finish();
            }
        });

    }
}
