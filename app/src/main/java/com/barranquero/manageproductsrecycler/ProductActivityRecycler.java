package com.barranquero.manageproductsrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.barranquero.manageproductsrecycler.adapter.ProductAdapterRecycler;


/**
 * Class which shows the product list
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class ProductActivityRecycler extends AppCompatActivity{
    private ProductAdapterRecycler mAdapter;
    private RecyclerView mRcvProduct;
    private static final int ADD_PRODUCT = 0;
    private static final int EDIT_PRODUCT = 1;
    private boolean ASC = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_recycler);

        mAdapter = new ProductAdapterRecycler(this);
        mRcvProduct = (RecyclerView)findViewById(R.id.rcvProduct);
        mRcvProduct.setLayoutManager(new LinearLayoutManager(this));
        mRcvProduct.setAdapter(mAdapter);
    }

    /**
     * Method which inflates the ActionBar menu
     * @param menu The Activity menu
     * @return true for success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
                intent = new Intent(ProductActivityRecycler.this, ManageProductFragment.class);
                startActivityForResult(intent, ADD_PRODUCT);
                mAdapter.notifyDataSetChanged();
                break;*/
            case R.id.action_sort_alphabetically:
                mAdapter.getAllProducts(3, ASC);
                ASC = !ASC;
                break;
            case R.id.action_settings_general:
                intent = new Intent(ProductActivityRecycler.this, GeneralSettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.action_settings_account:
                intent = new Intent(ProductActivityRecycler.this, AccountSettingsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PRODUCT){
            if (resultCode == RESULT_OK) {
                // Adding the product

            }
        } else if (requestCode == EDIT_PRODUCT) {
            if (resultCode == RESULT_OK) {
                // Editing the product
            }
        }
    }
}
