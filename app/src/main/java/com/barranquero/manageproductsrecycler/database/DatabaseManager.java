package com.barranquero.manageproductsrecycler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.barranquero.manageproductsrecycler.ManageProductsApplication;
import com.barranquero.manageproductsrecycler.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 23/01/17
 * ManageProductsFragment
 */

public class DatabaseManager {
    private static DatabaseManager databaseManager;
    private Context context;

    private DatabaseManager() {

    }

    public static DatabaseManager getInstance() {
        if (databaseManager == null)
            databaseManager = new DatabaseManager();
        return databaseManager;
    }

    public List<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        return products;
    }

    public Product getProduct(int id) {
        return null;
    }

    public void addProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DESCRIPTION, product.getmDescription());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_DOSAGE, product.getmDosage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IMAGE, product.getmImage());
        contentValues.put(ManageProductContract.ProductEntry.COLUMN_IDCATEGORY, 1);

        sqLiteDatabase.insert(ManageProductContract.ProductEntry.TABLE_NAME, null, contentValues);
        DatabaseHelper.getInstance().closeDatabase();
    }

    public void deleteProduct(Product product) {

    }
}
