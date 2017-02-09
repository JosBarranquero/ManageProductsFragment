package com.barranquero.manageproductsrecycler.cursor;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.barranquero.manageproductsrecycler.database.DatabaseManager;

/**
 * Created by usuario on 9/02/17
 * ManageProductsDatabase
 */

public class ProductCursorLoader extends CursorLoader {
    private Context context;

    public ProductCursorLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllProducts();
    }
}
