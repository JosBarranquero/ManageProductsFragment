package com.barranquero.manageproductsrecycler.interfaces;

import android.content.Context;
import android.database.Cursor;

import com.barranquero.manageproductsrecycler.adapter.PharmacyAdapter;

/**
 * Created by usuario on 30/01/17
 * ManageProductsFragment
 */

public interface PharmacyPresenter {
    interface Presenter {
        void getAllPharmacy(PharmacyAdapter adapter);
    }

    interface View {
        Context getContext();

        void setCursorPharmacy(Cursor cursor);
    }
}
