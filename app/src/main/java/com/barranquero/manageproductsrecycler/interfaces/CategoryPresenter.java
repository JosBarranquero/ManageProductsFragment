package com.barranquero.manageproductsrecycler.interfaces;

import android.content.Context;
import android.database.Cursor;
import android.widget.CursorAdapter;

/**
 * Created by usuario on 26/01/17
 * ManageProductsFragment
 */

public interface CategoryPresenter {
    void getAllCategory(CursorAdapter adapter);

    interface View {
        Context getContext();
        void setCursorCategory(Cursor cursor);

        Cursor getCursor();
    }
}
