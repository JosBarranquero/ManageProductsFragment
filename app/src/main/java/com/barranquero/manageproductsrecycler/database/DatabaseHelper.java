package com.barranquero.manageproductsrecycler.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

/**
 * Created by usuario on 20/01/17
 * ManageProductsFragment
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ManageProduct.db";
    private volatile static DatabaseHelper databaseHelper;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public synchronized static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ManageProductContract.CategoryEntry.SQL_CREATE_ENTRIES);
        db.execSQL(ManageProductContract.ProductEntry.SQL_CREATE_ENTRIES);
        db.execSQL(ManageProductContract.PharmacyEntry.SQL_CREATE_ENTRIES);
        db.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_CREATE_ENTRIES);
        db.execSQL(ManageProductContract.InvoiceEntry.SQL_CREATE_ENTRIES);
        db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ManageProductContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
        db.execSQL(ManageProductContract.InvoiceEntry.SQL_DELETE_ENTRIES);
        db.execSQL(ManageProductContract.InvoiceStatusEntry.SQL_DELETE_ENTRIES);
        db.execSQL(ManageProductContract.PharmacyEntry.SQL_DELETE_ENTRIES);
        db.execSQL(ManageProductContract.ProductEntry.SQL_DELETE_ENTRIES);
        db.execSQL(ManageProductContract.CategoryEntry.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, newVersion, oldVersion);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys = ON");
            }
        }
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
