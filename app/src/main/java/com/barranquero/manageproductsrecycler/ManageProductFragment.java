package com.barranquero.manageproductsrecycler;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.barranquero.manageproductsrecycler.database.ManageProductContract;
import com.barranquero.manageproductsrecycler.interfaces.CategoryPresenter;
import com.barranquero.manageproductsrecycler.interfaces.IProduct;
import com.barranquero.manageproductsrecycler.interfaces.ManagePresenter;
import com.barranquero.manageproductsrecycler.model.Product;
import com.barranquero.manageproductsrecycler.presenter.CategoryPresenterImpl;
import com.barranquero.manageproductsrecycler.presenter.ManagePresenterImpl;

import java.util.Locale;

/**
 * Class which adds a product to our list
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class ManageProductFragment extends Fragment implements ManagePresenter.View, CategoryPresenter.View {
    private EditText mEdtName, mEdtDesc, mEdtBrand, mEdtDosage, mEdtStock, mEdtPrice;
    private Button mBtnAddMed;
    private ImageButton mImgMedicine;
    private ManageProductListener manageProductListener;
    private ManagePresenterImpl managePresenter;
    private CategoryPresenter categoryPresenter;
    private Spinner spCategory;
    private SimpleCursorAdapter adapterCategory;
    private View parent;

    @Override
    public void showMessage(String message) {
        Snackbar.make(parent, getString(Integer.parseInt(message)), Snackbar.LENGTH_SHORT);
    }

    @Override
    public void setCursorCategory(Cursor cursor) {
        //adapterCategory.swapCursor(cursor);

        // Change closes the previous cursor and opens the new one
        adapterCategory.changeCursor(cursor);
    }

    public interface ManageProductListener {
        void showListProduct();
    }

    public static ManageProductFragment newInstance(Bundle bundle) {
        ManageProductFragment fragment = new ManageProductFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        managePresenter = new ManagePresenterImpl(this);
        categoryPresenter = new CategoryPresenterImpl(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        managePresenter = null;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            manageProductListener = (ManageProductListener)activity;
        } catch (ClassCastException ex) {
            throw new ClassCastException(ex.getMessage() + " activity must implement ManageProductListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        manageProductListener = null;
        adapterCategory = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_manage_product, container, false);

        parent = rootView.findViewById(R.id.fragment_add_product);

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
        spCategory = (Spinner)rootView.findViewById(R.id.spCategory);
        mBtnAddMed = (Button) rootView.findViewById(R.id.btnAddMed);


        final Product product;

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
                    updateProduct(product);
                    manageProductListener.showListProduct();
                }
            });
        } else {
            mBtnAddMed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveProduct();
                    manageProductListener.showListProduct();
                }
            });
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] from = {ManageProductContract.CategoryEntry.COLUMN_NAME};
        int[] to = {android.R.id.text1};
        adapterCategory = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_spinner_item, null, from, to, 0);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategory.setAdapter(adapterCategory);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Calls the presenter to load the data
        categoryPresenter.getAllCategory(adapterCategory);

    }

    private void saveProduct() {
        String name = mEdtName.getText().toString();
        String description = mEdtDesc.getText().toString();
        String brand = mEdtBrand.getText().toString();
        String dosage = mEdtDosage.getText().toString();
        double price = Double.parseDouble(mEdtPrice.getText().toString());
        int stock = Integer.parseInt(mEdtStock.getText().toString());
        int image = R.drawable.caja_medicamentos;
        Cursor cursor = ((SimpleCursorAdapter)spCategory.getAdapter()).getCursor();
        cursor.moveToPosition(spCategory.getSelectedItemPosition());

        Product product = new Product(name, description, brand, dosage, price, stock, image, cursor.getInt(0));

        managePresenter.saveProduct(product);
    }

    private void updateProduct(Product product) {
        String name = mEdtName.getText().toString();
        String description = mEdtDesc.getText().toString();
        String brand = mEdtBrand.getText().toString();
        String dosage = mEdtDosage.getText().toString();
        double price = Double.parseDouble(mEdtPrice.getText().toString());
        int stock = Integer.parseInt(mEdtStock.getText().toString());
        int image = R.drawable.caja_medicamentos;
        Cursor cursor = ((SimpleCursorAdapter)spCategory.getAdapter()).getCursor();
        cursor.moveToPosition(spCategory.getSelectedItemPosition());

        Product product1 = new Product(name, description, brand, dosage, price, stock, image, cursor.getInt(0));

        managePresenter.updateProduct(product, product1);
    }
}
