package com.barranquero.manageproductsrecycler;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

/**
 * Created by usuario on 16/12/16.
 */
public class SimpleMultipleChoiceModeListener implements AbsListView.MultiChoiceModeListener {
    Context myContext;
    private int myStatusBarColor;
    private int myCont = 0;

    public SimpleMultipleChoiceModeListener(Context context) {
        this.myContext = context;
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
        if (checked) {
            myCont++;
            //presenter.setNewSelection(position, checked);
        } else {
            myCont--;
            //presenter.removeSelection(position);
        }
        mode.setTitle(Integer.toString(myCont));
    }

    /**
     * When it creates
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
        return true;
    }

    /**
     * When it updates
     * @param mode
     * @param menu
     * @return
     */
    @Override
    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            myStatusBarColor = ((AppCompatActivity) myContext).getWindow().getStatusBarColor();
            ((AppCompatActivity)myContext).getWindow().setStatusBarColor(ContextCompat.getColor(myContext, R.color.colorError));
        }
        /*for (View v : listView) {
            v.setVisibility(View.INVISIBLE);
        }*/
        return true;
    }

    @Override
    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_product:
                //presenter.deleteSelectedProduct();
        }
        mode.finish();
        return true;
    }

    @Override
    public void onDestroyActionMode(ActionMode mode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((AppCompatActivity) myContext).getWindow().setStatusBarColor(myStatusBarColor);
        }
        //presenter.clearSelection();
        /*for (View v :
                listView) {
            v.setVisibility(View.VISIBLE);
        }*/
        myCont = 0;
    }
}
