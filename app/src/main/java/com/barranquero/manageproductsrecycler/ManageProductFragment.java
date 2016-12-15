package com.barranquero.manageproductsrecycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.barranquero.manageproductsrecycler.interfaces.IProduct;
import com.barranquero.manageproductsrecycler.model.Product;
import com.barranquero.manageproductsrecycler.presenter.ManagePresenterImpl;

import java.util.Locale;

/**
 * Class which adds a product to our list
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class ManageProductFragment extends Fragment {
    private EditText mEdtName, mEdtDesc, mEdtBrand, mEdtDosage, mEdtStock, mEdtPrice;
    private Button mBtnAddMed;
    private ImageButton mImgMedicine;
    private ManageProductListener mCallback;
    private ManagePresenterImpl mPresenter;

    public interface ManageProductListener {
        void showListProduct();
    }

    public static ManageProductFragment newInstance(Bundle bundle) {
        ManageProductFragment fragment = new ManageProductFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ManageProductListener)activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(ex.getMessage() + " activity must implement ManageProductListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_manage_product, container, false);

        mEdtName = (EditText)rootView.findViewById(R.id.edtName);
        mEdtDesc = (EditText)rootView. findViewById(R.id.edtDesc);
        mEdtBrand = (EditText) rootView.findViewById(R.id.edtBrand);
        mEdtDosage = (EditText) rootView.findViewById(R.id.edtDosage);
        mEdtStock = (EditText) rootView.findViewById(R.id.edtStock);
        mEdtPrice = (EditText) rootView.findViewById(R.id.edtPrice);
        mImgMedicine = (ImageButton) rootView.findViewById(R.id.imgMedicine);
        mImgMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(intent, 1);
                }
            }
        });

        mBtnAddMed = (Button) rootView.findViewById(R.id.btnAddMed);


        Product product;

        if (getArguments() != null) {
            product = getArguments().getParcelable(IProduct.PRODUCT_KEY);
            mEdtName.setText(product.getmName());
            mEdtDesc.setText(product.getmDescription());
            mEdtBrand.setText(product.getmBrand());
            mEdtDosage.setText(product.getmDosage());
            mEdtStock.setText(Integer.toString(product.getmStock()));
            mEdtPrice.setText(String.format(Locale.US, Double.toString(product.getmPrice())));
            mImgMedicine.setImageResource(product.getmImage());

            mBtnAddMed.setText(getResources().getString(R.string.edit_product));

            mBtnAddMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editProduct();
                }
            });
        } else {
            mBtnAddMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveProduct();
                }
            });
        }
        return null;
    }

    private void saveProduct() {
        String name = mEdtName.getText().toString();
        String description = mEdtDesc.getText().toString();
        String brand = mEdtBrand.getText().toString();
        String dosage = mEdtDosage.getText().toString();
        double price = Double.parseDouble(mEdtPrice.getText().toString());
        int stock = Integer.parseInt(mEdtStock.getText().toString());
        int image = R.drawable.cajaMedicamentos;

        Product product = new Product(name, description, brand, dosage, price, stock, image);

        Intent intent = new Intent();
        intent.putExtra(IProduct.PRODUCT_KEY, product);
        //setResult(Activity.RESULT_OK, intent);
        //finish();
    }

    private void editProduct() {
        String name = mEdtName.getText().toString();
        String description = mEdtDesc.getText().toString();
        String brand = mEdtBrand.getText().toString();
        String dosage = mEdtDosage.getText().toString();
        double price = Double.parseDouble(mEdtPrice.getText().toString());
        int stock = Integer.parseInt(mEdtStock.getText().toString());
        int image = R.drawable.cajaMedicamentos;

        Product product = new Product(name, description, brand, dosage, price, stock, image);

        Intent intent = new Intent();
        intent.putExtra(IProduct.PRODUCT_KEY, product);
        /*intent.putExtra(IProduct.OLD_KEY, getIntent().getExtras().getParcelable(Product.PRODUCT_KEY));
        setResult(Activity.RESULT_OK, intent);
        finish();*/
    }
}
