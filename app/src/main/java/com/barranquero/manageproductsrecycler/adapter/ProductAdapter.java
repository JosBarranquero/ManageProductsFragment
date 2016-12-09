package com.barranquero.manageproductsrecycler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.barranquero.manageproductsrecycler.ManageProductsApplication;
import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Adapter which manages a ViewHolder collection
 * It's not necessary to call notifyDataSetChanged() after add(), insert(), remove(), clear(), sort()... because these methods call it automatically and it uses the local copy
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    /**
     * Third param for super = ArrayList with Repository elements. A different local copy from the repository
     * @param context
     */
    public ProductAdapter(Context context) {
        super(context, R.layout.item_list_product, new ArrayList<>(((ManageProductsApplication)context.getApplicationContext()).getProducts()));
    }

    private static boolean ASC = true;

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ProductHolder productHolder;
        if (view == null) {
            //LayoutInflater layoutInflater = (LayoutInflater.from(context));
            LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_list_product, null);

            productHolder = new ProductHolder();
            productHolder.imgProduct = (ImageView)view.findViewById(R.id.imgProduct);
            productHolder.txvProductName = (TextView)view.findViewById(R.id.txvProductName);
            productHolder.txvProductPrice = (TextView)view.findViewById(R.id.txvProductPrice);
            productHolder.txvProductStock = (TextView)view.findViewById(R.id.txvProductStock);

            view.setTag(productHolder);
        } else {
            productHolder = (ProductHolder)view.getTag();
        }
        productHolder.imgProduct.setImageResource(getItem(position).getmImage());
        productHolder.txvProductName.setText(getItem(position).getmName());
        productHolder.txvProductPrice.setText(getItem(position).getFormattedPrice());
        productHolder.txvProductStock.setText(getItem(position).getFormattedStock());

        return view;
    }

    public void sortAlphabetically() {
        ASC = !ASC;
        if (ASC) {
            sort(Product.NAME_COMPARATOR);
        } else {
            sort(Collections.reverseOrder());
        }
    }

    public void updateProduct(List<Product> products) {
        clear();
        addAll(products);
    }

    public void addProduct(Product product) {
        add(product);
    }

    public void addProduct(Product product, Product old) {
        remove(old);
        add(product);
    }

    class ProductHolder {
        ImageView imgProduct;
        TextView txvProductName, txvProductPrice, txvProductStock;
    }
}
