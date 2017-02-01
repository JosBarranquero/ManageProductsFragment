package com.barranquero.manageproductsrecycler.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.barranquero.manageproductsrecycler.adapter.PharmacyAdapter;
import com.barranquero.manageproductsrecycler.cursor.PharmacyCursorLoader;
import com.barranquero.manageproductsrecycler.interfaces.PharmacyPresenter;

/**
 * Created by usuario on 30/01/17
 * ManageProductsFragment
 */
public class PharmacyPresenterImpl implements PharmacyPresenter.Presenter, LoaderManager.LoaderCallbacks<Cursor> {
    private static final int PHARMACY = 1;
    PharmacyPresenter.View view;
    Context context;

    public PharmacyPresenterImpl(PharmacyPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllPharmacy(PharmacyAdapter adapter) {
        ((Activity)context).getLoaderManager().initLoader(PHARMACY, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;
        switch (id) {
            case PHARMACY:
                loader = new PharmacyCursorLoader(context);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.setCursorPharmacy(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorPharmacy(null);
    }
}
