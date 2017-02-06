package com.barranquero.manageproductsrecycler.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

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
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}