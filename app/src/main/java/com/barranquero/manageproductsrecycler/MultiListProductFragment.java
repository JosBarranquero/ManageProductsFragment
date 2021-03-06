package com.barranquero.manageproductsrecycler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.barranquero.manageproductsrecycler.adapter.ProductAdapter;
import com.barranquero.manageproductsrecycler.interfaces.IProduct;
import com.barranquero.manageproductsrecycler.interfaces.ProductPresenter;
import com.barranquero.manageproductsrecycler.model.Product;
import com.barranquero.manageproductsrecycler.presenter.ProductPresenterImpl;
import com.barranquero.manageproductsrecycler.utils.SelectionUtils;

import java.util.List;


/**
 * Class which shows the product list
 *
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class MultiListProductFragment extends Fragment implements IProduct, ProductPresenter.View {
    private ProductAdapter mAdapter;
    private ListView mListProduct;
    private FloatingActionButton mFabAdd;
    private ListProductListener mCallback;
    private TextView mTxvNoData;
    private ProductPresenter mPresenter;

    public interface ListProductListener {
        void showManageProduct(Bundle bundle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ProductAdapter(getContext(), null, 1);
        mPresenter = new ProductPresenterImpl(this);

        setRetainInstance(true);
        setHasOptionsMenu(true);    //This option tells the Activity that the Fragment has its own menu and calls callback method onCreateOptionsMenu()

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListProductListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage() + " activity must implement ListProductListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdapter = null;
        mPresenter = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_list_product, container, false);

        mListProduct = (ListView) rootView.findViewById(android.R.id.list);

        mTxvNoData = (TextView)rootView.findViewById(android.R.id.empty);

        //registerForContextMenu(mListProduct);

        mFabAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.showManageProduct(null);
            }
        });

        return rootView;
    }

    /**
     * Method which inflates the ActionBar menu
     *
     * @param menu The Fragment menu
     * @return true for success
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_listproduct, menu);
    }

    /**
     * @param item The item that has been tapped on
     * @return true when the event controlled by this has been consumed, false when it hasn't and gets propagated
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            /*case R.id.action_add_product:
                intent = new Intent(ListProductFragment.this, ManageProductFragment.class);
                startActivityForResult(intent, ADD_PRODUCT);
                mAdapter.notifyDataSetChanged();
                break;*/
            case R.id.action_sort_alphabetically:
                //mAdapter.sortAlphabetically();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListProduct.setAdapter(mAdapter);
        mListProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(PRODUCT_KEY, (Product) parent.getItemAtPosition(position));
                mCallback.showManageProduct(bundle);
            }
        });
        mListProduct.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        SimpleMultipleChoiceModeListener mcl = new SimpleMultipleChoiceModeListener(getActivity());
        mListProduct.setMultiChoiceModeListener(mcl);
        final SelectionUtils selectionUtils = new SelectionUtils(mListProduct.getCheckedItemPositions());
        mListProduct.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selectionUtils.setSparseBoolean(mListProduct.getCheckedItemPositions());
                mListProduct.setItemChecked(position, !selectionUtils.isPositionChecker(position));
                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getAllProducts();
    }

    public void showProducts(List<Product> products) {
        //mAdapter.updateProduct(products);
    }

    private void hideList(boolean hide) {
        mListProduct.setVisibility(hide? View.GONE : View.VISIBLE);
        mTxvNoData.setVisibility(hide? View.VISIBLE : View.GONE);
    }

    public void showEmptyState(boolean show) {
        hideList(show);
    }

    public void showMessage(String message) {

    }

    public void showMessageDelete(final Product product) {
        Snackbar.make(getView(), "Producto eliminado", Snackbar.LENGTH_SHORT)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.addProduct(product);
                    }
                }).show();

        // SetCallback (calling a SnackBar callback method, even if the SnackBar has been deleted by Swiping
        /*.setCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                if ((event == DISMISS_EVENT_TIMEOUT) || (event == DISMISS_EVENT_SWIPE) || event == DISMISS_EVENT_MANUAL || event == DISMISS_EVENT_CONSECUTIVE) {
                //if (event != DISMISS_EVENT_ACTION) {
                    mPresenter.deleteFinallyProduct(product);
                }
            }
        }).show();*/
    }

    @Override
    public void setCursor(Cursor cursor) {
        try {
            mAdapter.changeCursor(cursor);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    public Cursor getCursor() {
        return mAdapter.getCursor();
    }
}
