package com.barranquero.manageproductsrecycler.provider;

import android.net.Uri;
import android.provider.BaseColumns;

import com.barranquero.manageproductsrecycler.database.DatabaseContract;

import static com.barranquero.manageproductsrecycler.database.DatabaseContract.ProductEntry.COLUMN_BRAND;
import static com.barranquero.manageproductsrecycler.database.DatabaseContract.ProductEntry.COLUMN_DESCRIPTION;
import static com.barranquero.manageproductsrecycler.database.DatabaseContract.ProductEntry.COLUMN_DOSAGE;
import static com.barranquero.manageproductsrecycler.database.DatabaseContract.ProductEntry.COLUMN_IDCATEGORY;
import static com.barranquero.manageproductsrecycler.database.DatabaseContract.ProductEntry.COLUMN_IMAGE;
import static com.barranquero.manageproductsrecycler.database.DatabaseContract.ProductEntry.COLUMN_PRICE;
import static com.barranquero.manageproductsrecycler.database.DatabaseContract.ProductEntry.COLUMN_STOCK;

/**
 * Class which stores the database schema
 * Created by usuario on 20/01/17
 * ManageProductsFragment
 */
public final class ManageProductContract {

    public static final String AUTHORITY = "com.barranquero.manageproductsrecycler";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);

    private ManageProductContract() {

    }

    public static class CategoryEntry implements BaseColumns {
        public static final String CONTENT_PATH = "category";

        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);

        public static final String COLUMN_NAME = "name";
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, COLUMN_NAME};
    }

    public static class ProductEntry implements BaseColumns {
        public static final String CONTENT_PATH = "product";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);

        public static final String COLUMN_NAME = "name";
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_BRAND, COLUMN_DOSAGE, COLUMN_PRICE, COLUMN_STOCK, COLUMN_IMAGE, COLUMN_IDCATEGORY};
    }

    public static class PharmacyEntry implements BaseColumns {
        public static final String CONTENT_PATH = "pharmacy";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);

        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, DatabaseContract.PharmacyEntry.COLUMN_ADDRESS, DatabaseContract.PharmacyEntry.COLUMN_CIF, DatabaseContract.PharmacyEntry.COLUMN_EMAIL, DatabaseContract.PharmacyEntry.COLUMN_PHONE};
    }

    public static class InvoiceStatusEntry implements BaseColumns {
        public static final String CONTENT_PATH = "invoiceStatus";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);

        public static final String COLUMN_NAME = "name";
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, DatabaseContract.InvoiceStatusEntry.COLUMN_NAME};
    }

    public static class InvoiceEntry implements BaseColumns {
        public static final String CONTENT_PATH = "invoice";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);

        public static final String COLUMN_IDPHARMACY = "id_pharmacy";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_STATUS = "status";
    }

    public static class InvoiceLineEntry implements BaseColumns {
        public static final String CONTENT_PATH = "invoiceLine";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, CONTENT_PATH);
    }
}
