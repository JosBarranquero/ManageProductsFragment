package com.barranquero.manageproductsrecycler.cursor;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import com.barranquero.manageproductsrecycler.database.DatabaseManager;

/**
 * Created by usuario on 27/01/17
 * ManageProductsFragment
 */

public class CategoryCursorLoader extends CursorLoader {

    public CategoryCursorLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllCategory();
    }
}
