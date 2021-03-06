package com.barranquero.manageproductsrecycler.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.provider.BaseColumns;
import android.util.Log;

import com.barranquero.manageproductsrecycler.model.Pharmacy;
import com.barranquero.manageproductsrecycler.model.Product;
import com.barranquero.manageproductsrecycler.provider.ManageProductContract;

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

    /**
     * Method which returns all products
     * @return List with all the products
     */
    public Cursor getAllProducts() {
        /*ArrayList<Product> products = new ArrayList<>();
        Product product;*/
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(DatabaseContract.ProductEntry.TABLE_NAME, ManageProductContract.ProductEntry.ALL_COLUMNS, null, null, null, null, null);

        /*if (cursor.moveToFirst()) {
            do {
                product = new Product();
                product.setmId(cursor.getInt(0));
                product.setmName(cursor.getString(1));
                product.setmDescription(cursor.getString(2));
                product.setmBrand(cursor.getString(3));
                product.setmDosage(cursor.getString(4));
                product.setmPrice(cursor.getDouble(5));
                product.setmStock(cursor.getInt(6));
                product.setmImage(cursor.getInt(7));
                product.setmCategory(cursor.getInt(8));

                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // Show in Log the product and category tables union
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DatabaseContract.ProductEntry.PRODUCT_JOIN_CATEGORY);
        cursor = queryBuilder.query(sqLiteDatabase, DatabaseContract.ProductEntry.COLUMNS_PRODUCT_JOIN_CATEGORY, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Log.d("PeHachePéMiAdmín", cursor.getString(0) + ", " + cursor.getString(1) + " -> " + cursor.getString(2));
            } while (cursor.moveToNext());
        }
        cursor.close();

        DatabaseHelper.getInstance().closeDatabase();
        return products;*/
        return cursor;
    }

    /**
     * Method which returns a single product
     * @param id    The product ID
     * @return      The product
     */
    public Product getProduct(int id) {
        return null;
    }

    /**
     * Method which adds a product in the database
     * @param product The product to be added
     */
    public void addProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION, product.getmDescription());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_DOSAGE, product.getmDosage());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_IMAGE, product.getmImage());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_IDCATEGORY, product.getmCategory());

        sqLiteDatabase.insert(DatabaseContract.ProductEntry.TABLE_NAME, null, contentValues);
        DatabaseHelper.getInstance().closeDatabase();
    }

    public void deleteProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        String where = BaseColumns._ID + " = ?";
        String[] whereArgs = new String[]{String.valueOf(product.getmId())};
        sqLiteDatabase.delete(DatabaseContract.ProductEntry.TABLE_NAME, where, whereArgs);
        DatabaseHelper.getInstance().closeDatabase();
    }

    public void updateProduct(Product product) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_NAME, product.getmName());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_DESCRIPTION, product.getmDescription());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_BRAND, product.getmBrand());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_DOSAGE, product.getmDosage());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_PRICE, product.getmPrice());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_STOCK, product.getmStock());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_IMAGE, product.getmImage());
        contentValues.put(DatabaseContract.ProductEntry.COLUMN_IDCATEGORY, product.getmCategory());

        String where = BaseColumns._ID + " = ?";
        String[] whereArgs = new String[] {String.valueOf(product.getmId())};

        sqLiteDatabase.update(DatabaseContract.ProductEntry.TABLE_NAME, contentValues, where, whereArgs);
        DatabaseHelper.getInstance().closeDatabase();
    }

    /**
     * Category table methods
     */
    public Cursor getAllCategory() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.CategoryEntry.TABLE_NAME, DatabaseContract.CategoryEntry.ALL_COLUMNS, null, null, null, null, null);
        //DatabaseHelper.getInstance().closeDatabase();
        return cursor;
    }

    public void addPharmacy(Pharmacy pharmacy) {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseContract.PharmacyEntry.COLUMN_CIF, pharmacy.getCif());
        contentValues.put(DatabaseContract.PharmacyEntry.COLUMN_ADDRESS, pharmacy.getAddress());
        contentValues.put(DatabaseContract.PharmacyEntry.COLUMN_EMAIL, pharmacy.getEmail());
        contentValues.put(DatabaseContract.PharmacyEntry.COLUMN_PHONE, pharmacy.getPhone());

        sqLiteDatabase.insert(DatabaseContract.PharmacyEntry.TABLE_NAME, null, contentValues);
        DatabaseHelper.getInstance().closeDatabase();
    }

    public Cursor getAllPharmacy() {
        SQLiteDatabase sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(DatabaseContract.PharmacyEntry.TABLE_NAME, ManageProductContract.PharmacyEntry.ALL_COLUMNS, null, null, null, null, null);
        return cursor;
    }
}
