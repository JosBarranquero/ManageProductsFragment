package com.barranquero.manageproductsrecycler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;

/**
 * Class to sign up a user
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class Sign_Activity extends AppCompatActivity{
    private Spinner spCounty, spCity;
    private RadioGroup rdgUserType;
    private TextInputLayout tilCompanyName;
    private AdapterView.OnItemSelectedListener spinnerLister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        spCity = (Spinner)findViewById(R.id.spCity);
        spCounty = (Spinner)findViewById(R.id.spCounty);

        rdgUserType = (RadioGroup)findViewById(R.id.rdgUserType);
        rdgUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbClient:
                        showCompany(false);
                        break;
                    case R.id.rbCompany:
                        showCompany(true);
                        break;
                }
            }
        });

        tilCompanyName = (TextInputLayout)findViewById(R.id.tilCompany);

        loadSpinnerCounty();
    }

    private void loadSpinnerCounty() {

        spinnerLister = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (view.getId()) {
                    case R.id.spCounty:
                        //TypedArray
                        break;
                    case R.id.spCity:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        //Initialise the County Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.provincias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCounty.setAdapter(adapter);
    }

    public void signUp(View view) {

    }

    /**
     * Method which shows the company name field
     * @param show True to show it, false to hide it
     */
    private void showCompany(boolean show) {
        tilCompanyName.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
