package com.barranquero.manageproductsrecycler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.barranquero.manageproductsrecycler.adapter.ProductAdapter;
import com.barranquero.manageproductsrecycler.interfaces.IProduct;
import com.barranquero.manageproductsrecycler.model.Product;


/**
 * Class which shows the product list
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class ListProductFragment extends Fragment implements IProduct {
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;
    private ProductAdapter mAdapter;
    private ListView mListProduct;
    private FloatingActionButton mFabAdd;
    private ListProductListener mCallback;

    public interface ListProductListener {
        void showManageProduct(Bundle bundle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (ListProductListener)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.getMessage() + " activity must implement ListProductListener interface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setContentView(R.layout.fragment_list_product);

        mListProduct = (ListView)findViewById(R.id.listProduct);

        mAdapter = new ProductAdapter(this);
        mListProduct.setAdapter(mAdapter);

        registerForContextMenu(mListProduct);

        mListProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                Intent intent = new Intent(ListProductFragment.this, ManageProductFragment.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_PRODUCT);
            }
        });

        mFabAdd = (FloatingActionButton)findViewById(R.id.fabAdd);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListProductFragment.this, ManageProductFragment.class);
                startActivityForResult(intent, ADD_PRODUCT);
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.listProduct) {
            ListView listView = (ListView)v;
            AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo)menuInfo;
            Product product = (Product)listView.getItemAtPosition(acmi.position);
            menu.setHeaderTitle(product.getmName());
            getMenuInflater().inflate(R.menu.menu_context, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()) {
            /*case R.id.action_delete_product:

                return true;
                break;*/
            default:
                return super.onContextItemSelected(item);
        }

    }

    /**
     * Method which inflates the ActionBar menu
     * @param menu The Activity menu
     * @return true for success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     * @param item The item that has been tapped on
     * @return true when the event controlled by this has been consumed, false when it hasn't and gets propagated
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            /*case R.id.action_add_product:
                intent = new Intent(ListProductFragment.this, ManageProductFragment.class);
                startActivityForResult(intent, ADD_PRODUCT);
                mAdapter.notifyDataSetChanged();
                break;*/
            case R.id.action_sort_alphabetically:
                mAdapter.sortAlphabetically();
                break;
            case R.id.action_settings_general:
                intent = new Intent(ListProductFragment.this, GeneralSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(ListProductFragment.this, AccountSettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PRODUCT){
            if (resultCode == RESULT_OK) {
                //Product product = (Product)data.getExtras().getParcelable(PRODUCT_KEY);
                Product product = (Product)data.getParcelableExtra(PRODUCT_KEY);
                ((ProductAdapter)mListProduct.getAdapter()).addProduct(product);
            }
        } else if (requestCode == EDIT_PRODUCT) {
            if (resultCode == RESULT_OK) {
                //Product product = (Product)data.getExtras().getParcelable(PRODUCT_KEY);
                //Product old = (Product)data.getExtras().getParcelable(OLD_KEY);
                Product product = (Product)data.getParcelableExtra(PRODUCT_KEY);
                Product old = (Product)data.getParcelableExtra(PRODUCT_KEY);
                ((ProductAdapter)mListProduct.getAdapter()).addProduct(product, old);
            }
        }
    }
}
