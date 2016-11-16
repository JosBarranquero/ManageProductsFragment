package com.barranquero.manageproductsrecycler;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.method.CharacterPickerDialog;
import android.util.Patterns;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.barranquero.manageproductsrecycler.interfaces.IValidateUser;
import com.barranquero.manageproductsrecycler.presenter.Signup_Presenter;

/**
 * Class to sign up a user
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class Sign_Activity extends AppCompatActivity implements IValidateUser.View {
    private Spinner spCounty, spCity;
    private RadioGroup rdgUserType;
    private TextInputLayout tilCompanyName;
    private AdapterView.OnItemSelectedListener spinnerLister;
    private Signup_Presenter presenter;
    private ViewGroup layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        layout = (ViewGroup)findViewById(R.id.activity_sign;

        spCity = (Spinner)findViewById(R.id.spCity);
        spCounty = (Spinner)findViewById(R.id.spCounty);

        rdgUserType = (RadioGroup)findViewById(R.id.rdgUserType);

        initUserType();



        tilCompanyName = (TextInputLayout)findViewById(R.id.tilCompany);

        loadSpinnerCounty();
    }

    private void initUserType() {
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
    }

    private void loadSpinnerCounty() {

        spinnerLister = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // No puede ser el view.getId()
                switch (parent.getId()) {
                    case R.id.spCounty:
                        loadSpinnerCity(position);
                        break;
                    case R.id.spCity:
                        showCitySelected();
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
        spCounty.setOnItemSelectedListener(spinnerLister);
        spCounty.setAdapter(adapter);
    }

    private void loadSpinnerCity(int position) {
        TypedArray typedArray = getResources().obtainTypedArray(R.array.array_provincia_a_localidades);
        CharSequence[] city = typedArray.getTextArray(position);
        typedArray.recycle();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(Sign_Activity.this, android.R.layout.simple_spinner_item, city);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(adapter);
    }

    private void showCitySelected() {
        String mensaje = String.format(getString(R.string.message_county_city), spCounty.getSelectedItem().toString(),spCity.getSelectedItem().toString());
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
    }

    /**
     * Method which shows the company name field
     * @param show True to show it, false to hide it
     */
    private void showCompany(boolean show) {
        tilCompanyName.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public void signUp(View view) {
        //Recoger los datos de la vista y llama al método del presentador
        User user = new User();
        presenter.validateCredentials(user);
    }

    private boolean validate(String email) {

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void setMessageError(String error, int idView) {
        String error = getResources().getString(getResources().getIdentifier(nameResource, "string", getPackageName()));
        switch (idView) {
            case R.id.tilPassword:
                //mTilPassword.setError(error);
                Snackbar.make(layout, error, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.tilUser:
                //mTilUser.setError(error);
                Snackbar.make(layout, error, Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.tilEmail:
                Snackbar.make(layout, error, Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void startActivity() {

    }
}
