package com.barranquero.manageproductsrecycler.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.barranquero.manageproductsrecycler.ManageProductsApplication;
import com.barranquero.manageproductsrecycler.provider.ManageProductContract;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by usuario on 20/01/17
 * ManageProductsFragment
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "ManageProduct.db";
    private volatile static DatabaseHelper databaseHelper;
    private AtomicInteger mOpenCounter;
    private SQLiteDatabase mDatabase;

    private DatabaseHelper() {
        super(ManageProductsApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
        mOpenCounter = new AtomicInteger();
    }

    public synchronized static DatabaseHelper getInstance() {
        if (databaseHelper == null) {
            databaseHelper = new DatabaseHelper();
        }
        return databaseHelper;
    }

    public synchronized SQLiteDatabase openDatabase() {
        if (mOpenCounter.incrementAndGet() == 1) {
            mDatabase = getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        if (mOpenCounter.decrementAndGet() == 0) {
            mDatabase.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(DatabaseContract.CategoryEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.CategoryEntry.SQL_INSERT_DEFAULT);
            db.execSQL(DatabaseContract.ProductEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.ProductEntry.SQL_INSERT_DEFAULT);
            db.execSQL(DatabaseContract.PharmacyEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.PharmacyEntry.SQL_INSERT_DEFAULT);
            db.execSQL(DatabaseContract.InvoiceStatusEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceEntry.SQL_CREATE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceLineEntry.SQL_CREATE_ENTRIES);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("Manage", "Error:" + e.getMessage());
        } finally {
            db.endTransaction();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransaction();
        try {
            db.execSQL(DatabaseContract.InvoiceLineEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.InvoiceStatusEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.PharmacyEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.ProductEntry.SQL_DELETE_ENTRIES);
            db.execSQL(DatabaseContract.CategoryEntry.SQL_DELETE_ENTRIES);
            onCreate(db);
            db.setTransactionSuccessful();
        } catch (SQLiteException e) {
            Log.e("Manage", "Error:" + e.getMessage());
        } finally {
            db.endTransaction();
        }
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

    @Override
    public synchronized void close() {
        super.close();
    }

    public SQLiteDatabase open() {
        return getWritableDatabase();
    }
}
