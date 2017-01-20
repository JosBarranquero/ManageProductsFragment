package com.barranquero.manageproductsrecycler.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.barranquero.manageproductsrecycler.ManageProductsApplication;
import com.barranquero.manageproductsrecycler.ProductRepository;
import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter which manages a ViewHolder collection
 */
public class ProductAdapterRecycler extends RecyclerView.Adapter<ProductAdapterRecycler.ProductViewHolder> {
    private List<Product> products;
    private Context context;

    public ProductAdapterRecycler(Context c) {
        this.context = c;
        products = ProductRepository.getInstance().getProducts();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // False creates the RelativeLayout in the XML file (item_list_product.xml)
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product, null);
        return new ProductViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.product_image.setImageResource(products.get(position).getmImage());
        holder.txvName.setText(products.get(position).getmName());
        holder.txvStock.setText(products.get(position).getFormattedStock());
        holder.txvPrice.setText(products.get(position).getFormattedPrice());
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView product_image;
        TextView txvName;
        TextView txvStock;
        TextView txvPrice;

        public ProductViewHolder(View itemView) {
            super(itemView);
            product_image = (ImageView)itemView.findViewById(R.id.imgProduct);
            txvName = (TextView)itemView.findViewById(R.id.txvProductName);
            txvStock = (TextView)itemView.findViewById(R.id.txvProductStock);
            txvPrice = (TextView)itemView.findViewById(R.id.txvProductPrice);
        }
    }

    public void getAllProducts(int option, boolean ASC){
        products.clear();
        products.addAll(ProductRepository.getInstance().getProducts());
        // The view gets notified. Viewable-viewer pattern
        notifyDataSetChanged();
    }
}
