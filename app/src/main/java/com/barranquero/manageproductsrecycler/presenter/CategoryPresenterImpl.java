package com.barranquero.manageproductsrecycler.presenter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.widget.CursorAdapter;

import com.barranquero.manageproductsrecycler.cursor.CategoryCursorLoader;
import com.barranquero.manageproductsrecycler.interfaces.CategoryPresenter;

/**
 * Created by usuario on 26/01/17
 * ManageProductsFragment
 */

public class CategoryPresenterImpl implements CategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private CategoryPresenter.View view;
    private Context context;
    public static final int CATEGORY = 1;

    public CategoryPresenterImpl(CategoryPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    @Override
    public void getAllCategory(CursorAdapter adapter) {
        //Cursor cursor = DatabaseManager.getInstance().getAllCategory();
        //adapter.swapCursor(cursor);
        //view.getAdapterView().getAdapter().swapCursor(cursor);
        ((Activity)context).getLoaderManager().initLoader(CATEGORY, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;
        switch (id) {
            case CATEGORY:
                loader = new CategoryCursorLoader(context);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursorCategory(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorCategory(null);
    }
}
