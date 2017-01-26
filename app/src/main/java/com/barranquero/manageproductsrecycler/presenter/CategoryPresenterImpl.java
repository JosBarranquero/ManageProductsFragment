package com.barranquero.manageproductsrecycler.presenter;

import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;

import com.barranquero.manageproductsrecycler.database.DatabaseManager;
import com.barranquero.manageproductsrecycler.interfaces.CategoryPresenter;

/**
 * Created by usuario on 26/01/17
 * ManageProductsFragment
 */

public class CategoryPresenterImpl implements CategoryPresenter {
    private CategoryPresenter.View view;

    public CategoryPresenterImpl(CategoryPresenter.View view) {
        this.view = view;
    }

    @Override
    public void getAllCategory(CursorAdapter adapter) {
        Cursor cursor = DatabaseManager.getInstance().getAllCategory();
        adapter.swapCursor(cursor);
        //view.getAdapterView().getAdapter().swapCursor(cursor);
    }
}
