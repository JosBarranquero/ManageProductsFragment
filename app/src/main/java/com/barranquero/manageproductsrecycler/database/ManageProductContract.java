package com.barranquero.manageproductsrecycler.database;

import android.provider.BaseColumns;

/**
 * Class which stores the database schema
 * Created by usuario on 20/01/17
 * ManageProductsFragment
 */
public final class ManageProductContract {

    private ManageProductContract() {

    }

    public static class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME = "name";
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, COLUMN_NAME};
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL)", TABLE_NAME, BaseColumns._ID, COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        public static final String SQL_INSERT_DEFAULT = String.format("INSERT INTO %s VALUES (1, 'Farmaco'), (2, 'Parafarmacia'), (3, 'Drogas duras')", TABLE_NAME);
    }

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_BRAND = "brand";
        public static final String COLUMN_DOSAGE = "dosage";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_STOCK = "stock";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_IDCATEGORY = "id_category";
        public static final String PRODUCT_JOIN_CATEGORY = String.format("%s INNER JOIN %s ON %s=%s.%s",
                TABLE_NAME, CategoryEntry.TABLE_NAME, COLUMN_IDCATEGORY, CategoryEntry.TABLE_NAME, BaseColumns._ID);
        public static final String[] COLUMNS_PRODUCT_JOIN_CATEGORY = {
                TABLE_NAME+"."+COLUMN_NAME, COLUMN_DESCRIPTION, CategoryEntry.TABLE_NAME+"."+CategoryEntry.COLUMN_NAME
        };
        public static final String[] ALL_COLUMNS = new String[]{BaseColumns._ID, COLUMN_NAME, COLUMN_DESCRIPTION, COLUMN_BRAND, COLUMN_DOSAGE, COLUMN_PRICE, COLUMN_STOCK, COLUMN_IMAGE, COLUMN_IDCATEGORY};
        public static final String REFERENCE_ID_CATEGORY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", CategoryEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s REAL NOT NULL," +
                        "%s INTEGER NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL %s )",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME,
                COLUMN_DESCRIPTION,
                COLUMN_BRAND,
                COLUMN_DOSAGE,
                COLUMN_PRICE,
                COLUMN_STOCK,
                COLUMN_IMAGE,
                COLUMN_IDCATEGORY,
                REFERENCE_ID_CATEGORY);
        public static final String SQL_INSERT_DEFAULT = String.format("INSERT INTO %s VALUES (1, 'Coca', 'La del Rivera', 'Nesquick', '250g', 7.50, 1, 54646345, 3), (2, 'Mi medicación', 'La de Paco', 'Apache2 roto', '250mg', 17.50, 13, 54646345, 1)", TABLE_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class PharmacyEntry implements BaseColumns {
        public static final String TABLE_NAME = "pharmacy";
        public static final String COLUMN_CIF = "cif";
        public static final String COLUMN_ADDRESS = "address";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_EMAIL = "email";
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_CIF,
                COLUMN_ADDRESS,
                COLUMN_PHONE,
                COLUMN_EMAIL
        );
        public static final String SQL_INSERT_DEFAULT = String.format("INSERT INTO %s VALUES (1, '2313FF', 'Calle falsa', '95223213', 'micorreo@pacog.es')", TABLE_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        public static final String[] ALL_COLUMNS = {BaseColumns._ID, COLUMN_CIF, COLUMN_ADDRESS, COLUMN_PHONE, COLUMN_EMAIL};
    }

    public static class InvoiceStatusEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoiceStatus";
        public static final String COLUMN_NAME = "name";
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_NAME);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class InvoiceEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoice";
        public static final String COLUMN_IDPHARMACY = "id_pharmacy";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_STATUS = "status";
        public static final String REFERENCE_ID_PHARMACY = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", PharmacyEntry.TABLE_NAME, BaseColumns._ID);
        public static final String REFERENCE_STATUS = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", InvoiceStatusEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                        "%s INTEGER NOT NULL %s," +
                        "%s TEXT NOT NULL," +
                        "%s INTEGER NOT NULL %s)",
                TABLE_NAME, BaseColumns._ID,
                COLUMN_IDPHARMACY, REFERENCE_ID_PHARMACY,
                COLUMN_DATE,
                COLUMN_STATUS, REFERENCE_STATUS);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }

    public static class InvoiceLineEntry implements BaseColumns {
        public static final String TABLE_NAME = "invoiceLine";
        public static final String COLUMN_IDINVOICE = "id_invoice";
        public static final String COLUMN_ORDERPRODUCT = "order_product";
        public static final String COLUMN_IDPRODUCT = "id_product";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_PRICE = "price";
        public static final String REFERENCE_ID_INVOICE = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", InvoiceEntry.TABLE_NAME, BaseColumns._ID);
        public static final String REFERENCE_ID_PRODUCT = String.format("REFERENCES %s (%s) ON UPDATE CASCADE ON DELETE RESTRICT", ProductEntry.TABLE_NAME, BaseColumns._ID);
        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s (%s INTEGER NOT NULL %s," +
                        "%s INTEGER NOT NULL," +
                        "%s INTEGER NOT NULL %s," +
                        "%s INTEGER NOT NULL," +
                        "%s REAL NOT NULL)",
                TABLE_NAME, COLUMN_IDINVOICE, REFERENCE_ID_INVOICE,
                COLUMN_ORDERPRODUCT,
                COLUMN_IDPRODUCT, REFERENCE_ID_PRODUCT,
                COLUMN_QUANTITY,
                COLUMN_PRICE);
        public static final String SQL_DELETE_ENTRIES = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
    }
}
