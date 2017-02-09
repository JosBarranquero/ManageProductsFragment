package com.barranquero.manageproductsrecycler.presenter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.app.LoaderManager;
import android.content.Loader;
import android.content.CursorLoader;
import android.widget.CursorAdapter;

import com.barranquero.manageproductsrecycler.cursor.CategoryCursorLoader;
import com.barranquero.manageproductsrecycler.database.DatabaseContract;
import com.barranquero.manageproductsrecycler.interfaces.CategoryPresenter;
import com.barranquero.manageproductsrecycler.model.Category;
import com.barranquero.manageproductsrecycler.provider.ManageProductContract;

/**
 * Created by usuario on 26/01/17
 * ManageProductsFragment
 */

public class CategoryPresenterImpl implements CategoryPresenter, LoaderManager.LoaderCallbacks<Cursor> {
    private CategoryPresenter.View view;
    private Context context;
    public static final int CATEGORY = 2;

    public CategoryPresenterImpl(CategoryPresenter.View view) {
        this.view = view;
        this.context = view.getContext();
    }

    public void add(Category category) {
        try {
            ContentValues contentValues = getContentCategory(category);
            context.getContentResolver().insert(ManageProductContract.CategoryEntry.CONTENT_URI, contentValues);
        } catch (SQLiteException e) {
            e.getMessage();
        }
    }

    private ContentValues getContentCategory(Category category) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ManageProductContract.CategoryEntry.COLUMN_NAME, category.getDescription());
        return contentValues;
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
        Loader loader = null;
        switch (id) {
            case CATEGORY:
                loader = new CursorLoader(context, ManageProductContract.CategoryEntry.CONTENT_URI, ManageProductContract.CategoryEntry.ALL_COLUMNS, null, null, DatabaseContract.CategoryEntry.DEFAULT_SORT);
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        view.setCursorCategory(cursor);
        view.getCursor().setNotificationUri(context.getContentResolver(), ManageProductContract.CategoryEntry.CONTENT_URI);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursorCategory(null);
    }
}
