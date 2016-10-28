package com.barranquero.manageproductsrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.barranquero.manageproductsrecycler.model.Product;

/**
 * Class which adds a product to our list
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class AddProduct_Activity extends AppCompatActivity {
    private EditText mEdtName, mEdtDesc, mEdtBrand, mEdtDosage, mEdtStock, mEdtPrice;
    private Button mBtnAddMed;
    private ImageButton mImgMedicine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        mEdtName = (EditText)findViewById(R.id.edtName);
        mEdtDesc = (EditText)findViewById(R.id.edtDesc);
        mEdtBrand = (EditText)findViewById(R.id.edtBrand);
        mEdtDosage = (EditText)findViewById(R.id.edtDosage);
        mEdtStock = (EditText)findViewById(R.id.edtStock);
        mEdtPrice = (EditText)findViewById(R.id.edtPrice);

        mImgMedicine = (ImageButton)findViewById(R.id.imgMedicine);
        mImgMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, 1);

                }
            }
        });

        mBtnAddMed = (Button)findViewById(R.id.btnAddMed);
        mBtnAddMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mEdtName.getText().toString();
                String description = mEdtDesc.getText().toString();
                String brand = mEdtBrand.getText().toString();
                String dosage = mEdtDosage.getText().toString();
                double price = Double.parseDouble(mEdtPrice.getText().toString());
                int stock = Integer.parseInt(mEdtStock.getText().toString());
                int image = mImgMedicine.getId();

                Product product = new Product(name, description, brand, dosage, price, stock, image);
                ((ManageProducts_Application)getApplicationContext()).addProduct(product);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

            }
        }
    }
}
