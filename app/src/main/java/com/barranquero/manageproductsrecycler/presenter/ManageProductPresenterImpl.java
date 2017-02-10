package com.barranquero.manageproductsrecycler.presenter;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

import com.barranquero.manageproductsrecycler.database.DatabaseContract;
import com.barranquero.manageproductsrecycler.interfaces.ManageProductPresenter;
import com.barranquero.manageproductsrecycler.model.Category;
import com.barranquero.manageproductsrecycler.model.Product;
import com.barranquero.manageproductsrecycler.provider.ManageProductContract;

/**
 * Created by usuario on 12/12/16.
 */
public class ManageProductPresenterImpl implements ManageProductPresenter {
    ManageProductPresenter.View myView;

    public ManageProductPresenterImpl(ManageProductPresenter.View view) {
        this.myView = view;
    }

    @Override
    public void saveProduct(Product product) {
        /*DatabaseManager myRepository = DatabaseManager.getInstance();
        if (myRepository.getAllProducts().contains(product))
            myView.showMessage(Integer.toString(R.string.already_exists));
        else
            myRepository.addProduct(product);*/
        try {
            ContentValues contentValues = getContentCategory(product);
            myView.getContext().getContentResolver().insert(ManageProductContract.ProductEntry.CONTENT_URI, contentValues);
        } catch (SQLiteException e) {
            e.getMessage();
        }
    }

    @Override
    public void updateProduct(Product oldOne, Product newOne) {
        /*DatabaseManager.getInstance().deleteProduct(oldOne);
        DatabaseManager.getInstance().addProduct(newOne);*/
        Uri uri = null;
        try {
            uri = ContentUris.withAppendedId(ManageProductContract.PharmacyEntry.CONTENT_URI, oldOne.getmId());
            ContentValues contentValues =getContentCategory(newOne);
            myView.getContext().getContentResolver().update(uri, contentValues, null, null);
        } catch (Exception e) {
            Log.e("PHPMiAdmín", e.getLocalizedMessage());
        }
    }

    @Override
    public void onDestroy() {
        this.myView = null;
    }

    private ContentValues getContentCategory(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION, product.getmDescription());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_DOSAGE, product.getmDosage());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_IMAGE, product.getmImage());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_IDCATEGORY, product.getmCategory());
        return contentValues;
    }
}
