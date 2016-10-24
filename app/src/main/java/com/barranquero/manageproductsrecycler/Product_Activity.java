package com.barranquero.manageproductsrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.barranquero.manageproductsrecycler.adapter.ProductAdapterRecycler;


/**
 * Class which shows the product list
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 */
public class Product_Activity extends AppCompatActivity{
    private ProductAdapterRecycler mAdapter;
    private RecyclerView mRcvProduct;
    private Button mBtnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        mAdapter = new ProductAdapterRecycler(this);
        mRcvProduct = (RecyclerView)findViewById(R.id.rcvProduct);
        mRcvProduct.setLayoutManager(new LinearLayoutManager(this));
        mRcvProduct.setAdapter(mAdapter);

        mBtnAddProduct = (Button)findViewById(R.id.btnAddProduct);
        mBtnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.barranquero.manageproductsrecycler.Product_Activity.this, AddProduct_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new ProductAdapterRecycler(this);
        mRcvProduct.setLayoutManager(new LinearLayoutManager(this));
        mRcvProduct.setAdapter(mAdapter);
    }
}
