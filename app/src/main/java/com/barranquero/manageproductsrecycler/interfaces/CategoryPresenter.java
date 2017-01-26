package com.barranquero.manageproductsrecycler.interfaces;

import android.support.v4.widget.CursorAdapter;

/**
 * Created by usuario on 26/01/17
 * ManageProductsFragment
 */

public interface CategoryPresenter {
    void getAllCategory(CursorAdapter adapter);

    interface View {

    }
}
