package com.barranquero.manageproductsrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
public class Product_Activity extends AppCompatActivity implements IProduct {
    private ProductAdapter mAdapter;
    private ListView mListProduct;
    private FloatingActionButton mFabAdd;
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mListProduct = (ListView)findViewById(R.id.listProduct);

        mAdapter = new ProductAdapter(this);
        mListProduct.setAdapter(mAdapter);

        registerForContextMenu(mListProduct);

        mListProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Bundle bundle = new Bundle();
                bundle.putSerializable(PRODUCT_KEY, (Product)parent.getItemAtPosition(position));
                Intent intent = new Intent(Product_Activity.this, ManageProduct_Activity.class);
                intent.putExtras(bundle);
                startActivityForResult(intent, EDIT_PRODUCT);
            }
        });

        mFabAdd = (FloatingActionButton)findViewById(R.id.fabAdd);
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Product_Activity.this, ManageProduct_Activity.class);
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
            case R.id.action_delete_product:
                
                return true;
                break;
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
                intent = new Intent(Product_Activity.this, ManageProduct_Activity.class);
                startActivityForResult(intent, ADD_PRODUCT);
                mAdapter.notifyDataSetChanged();
                break;*/
            case R.id.action_sort_alphabetically:
                mAdapter.sortAlphabetically();
                break;
            case R.id.action_settings_general:
                intent = new Intent(Product_Activity.this, GeneralSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(Product_Activity.this, AccountSettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PRODUCT){
            if (resultCode == RESULT_OK) {
                Product product = (Product)data.getExtras().getSerializable(PRODUCT_KEY);
                ((ProductAdapter)mListProduct.getAdapter()).addProduct(product);
            }
        } else if (requestCode == EDIT_PRODUCT) {
            if (resultCode == RESULT_OK) {
                Product product = (Product)data.getExtras().getSerializable(PRODUCT_KEY);
                Product old = (Product)data.getExtras().getSerializable(OLD_KEY);
                ((ProductAdapter)mListProduct.getAdapter()).addProduct(product, old);
            }
        }
    }
}
