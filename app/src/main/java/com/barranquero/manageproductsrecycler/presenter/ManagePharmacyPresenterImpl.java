package com.barranquero.manageproductsrecycler.presenter;

import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.util.Log;

import com.barranquero.manageproductsrecycler.interfaces.ManagePharmacyPresenter;
import com.barranquero.manageproductsrecycler.model.Pharmacy;
import com.barranquero.manageproductsrecycler.provider.ManageProductContract;

/**
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 *          ManageProductsFragment
 */

public class ManagePharmacyPresenterImpl implements ManagePharmacyPresenter {
    ManagePharmacyPresenter.View myView;

    public ManagePharmacyPresenterImpl(ManagePharmacyPresenter.View view) {
        this.myView = view;
    }

    @Override
    public void savePharmacy(Pharmacy pharmacy) {
        /*DatabaseManager myRepository = DatabaseManager.getInstance();
        if (myRepository.getAllProducts().contains(pharmacy))
            myView.showMessage(Integer.toString(R.string.already_exists));
        else
            myRepository.addPharmacy(pharmacy);*/
    }

    @Override
    public void updatePharmacy(Pharmacy oldOne, Pharmacy newOne) {
        //TODO DatabaseManager.getInstance().deletePharmacy(oldOne);
        //DatabaseManager.getInstance().addPharmacy(newOne);
        Uri uri = null;
        try {
            uri = ContentUris.withAppendedId(ManageProductContract.PharmacyEntry.CONTENT_URI, oldOne.getId());
            //ContentValues contentValues =getContentCategory(newOne);
            //myView.getContext().getContentResolver().update(uri, contentValues, null, null);
        } catch (Exception e) {
            Log.e("PHPMiAdmín", e.getLocalizedMessage());
        }
    }

    @Override
    public void onDestroy() {
        this.myView = null;
    }
}
