package com.barranquero.manageproductsrecycler.cursor;

import android.content.Context;
import android.database.Cursor;
import android.content.CursorLoader;

import com.barranquero.manageproductsrecycler.database.DatabaseManager;

/**
 * Created by usuario on 30/01/17
 * ManageProductsFragment
 */

public class PharmacyCursorLoader extends CursorLoader {
    private Context context;

    public PharmacyCursorLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public Cursor loadInBackground() {
        return DatabaseManager.getInstance().getAllPharmacy();
    }
}
