package com.barranquero.manageproductsrecycler.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.database.DatabaseContract;
import com.barranquero.manageproductsrecycler.database.DatabaseHelper;

/**
 * Created by usuario on 6/02/17
 * ManageProductsDatabase
 */

public class ManageProductProvider extends ContentProvider {
    public static final int PRODUCT = 1;
    public static final int PRODUCT_ID = 2;
    public static final int CATEGORY = 3;
    public static final int CATEGORY_ID = 4;
    public static final int INVOICESTATUS = 5;
    public static final int INVOICESTATUS_ID = 6;
    public static final int PHARMACY = 7;
    public static final int PHARMACY_ID = 8;
    public static final int INVOICELINE = 9;
    public static final int INVOICELINE_ID = 10;
    public static final int INVOICE = 11;
    public static final int INVOICE_ID = 11;

    private SQLiteDatabase sqLiteDatabase;

    public static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.ProductEntry.CONTENT_PATH, PRODUCT);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.ProductEntry.CONTENT_PATH+"/#", PRODUCT_ID);

        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.CategoryEntry.CONTENT_PATH, CATEGORY);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.CategoryEntry.CONTENT_PATH+"/#", CATEGORY_ID);

        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatusEntry.CONTENT_PATH, INVOICESTATUS);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceStatusEntry.CONTENT_PATH+"/#", INVOICESTATUS_ID);

        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.PharmacyEntry.CONTENT_PATH, PHARMACY);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.PharmacyEntry.CONTENT_PATH+"/#", PHARMACY_ID);

        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLineEntry.CONTENT_PATH, INVOICELINE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceLineEntry.CONTENT_PATH+"/#", INVOICELINE_ID);

        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceEntry.CONTENT_PATH, INVOICE);
        uriMatcher.addURI(ManageProductContract.AUTHORITY, ManageProductContract.InvoiceEntry.CONTENT_PATH+"/#", INVOICE_ID);
    }

    @Override
    public boolean onCreate() {
        sqLiteDatabase = DatabaseHelper.getInstance().openDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        String rowId;
        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                sqLiteQueryBuilder.setTables(DatabaseContract.CategoryEntry.TABLE_NAME);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = DatabaseContract.CategoryEntry.DEFAULT_SORT;
                }
                break;
            case CATEGORY_ID:
                sqLiteQueryBuilder.setTables(DatabaseContract.CategoryEntry.TABLE_NAME);;
                rowId = uri.getPathSegments().get(1);
                selection = DatabaseContract.CategoryEntry._ID+"=?";
                selectionArgs = new String[]{rowId};
                //sqLiteDatabase.query(DatabaseContract.CategoryEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PRODUCT:
                sqLiteQueryBuilder.setTables(DatabaseContract.ProductEntry.TABLE_NAME);
                sqLiteQueryBuilder.setProjectionMap(ManageProductContract.ProductEntry.sProductProjectionMapM);
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = DatabaseContract.ProductEntry.DEFAULT_SORT;
                }
                break;
            case PRODUCT_ID:
                break;
            case UriMatcher.NO_MATCH:
                throw new IllegalArgumentException("Invalid URI");
        }
        String sqlQuery = sqLiteQueryBuilder.buildQuery(projection, selection, null, null, sortOrder, null);
        Log.i("MiEseCuEle", sqlQuery);
        Cursor cursor = sqLiteQueryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        String mimeType = null;
        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                mimeType = "vnd.android.cursor.dir/vnd.barranquero.manageproductsrecycler.category";
                break;
            case CATEGORY_ID:
                mimeType = "vnd.android.cursor.item/vnd.barranquero.manageproductsrecycler.category";
                break;
            case PRODUCT:
                mimeType = "vnd.android.cursor.dir/vnd.barranquero.manageproductsrecycler.product";
                break;
            case PRODUCT_ID:
                mimeType = "vnd.android.cursor.item/vnd.barranquero.manageproductsrecycler.product";
                break;
        }
        return mimeType;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri newUri = null;
        long regId = -1;
        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                regId = sqLiteDatabase.insert(DatabaseContract.CategoryEntry.TABLE_NAME, null, values);
                newUri = ContentUris.withAppendedId(uri, regId);
                break;
            case PRODUCT:
                regId = sqLiteDatabase.insert(DatabaseContract.ProductEntry.TABLE_NAME, null, values);
                newUri = ContentUris.withAppendedId(uri, regId);
                break;
        }
        if (regId != -1) {
            // Notify the observers that an URI was modified
            getContext().getContentResolver().notifyChange(newUri, null);
        } else {
            throw new SQLiteException(getContext().getResources().getString(R.string.error_insert));
        }
        return newUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int affected = -1;
        String rowId;
        switch (uriMatcher.match(uri)) {
            case CATEGORY:
                affected = sqLiteDatabase.update(DatabaseContract.CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case CATEGORY_ID:                                    //POS: 0    1
                // content://com.barranquero.manageproductsrecycler/category/1
                rowId = uri.getPathSegments().get(1);
                selection = DatabaseContract.CategoryEntry._ID+"=?";
                selectionArgs = new String[]{rowId};
                affected = sqLiteDatabase.update(DatabaseContract.CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case PRODUCT:
                affected = sqLiteDatabase.update(DatabaseContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case PRODUCT_ID:                                    //POS: 0    1
            // content://com.barranquero.manageproductsrecycler/category/1
            rowId = uri.getPathSegments().get(1);
            selection = DatabaseContract.ProductEntry._ID+"=?";
            selectionArgs = new String[]{rowId};
            affected = sqLiteDatabase.update(DatabaseContract.ProductEntry.TABLE_NAME, values, selection, selectionArgs);
            break;
        }
        if (affected != -1) {
            // Notify the observers that an URI was modified
            getContext().getContentResolver().notifyChange(uri, null);
        } else {
            throw new SQLiteException(getContext().getResources().getString(R.string.error_update));
        }
        return affected;
    }
}
