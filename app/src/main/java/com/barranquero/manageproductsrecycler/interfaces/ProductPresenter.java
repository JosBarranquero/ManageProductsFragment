package com.barranquero.manageproductsrecycler.interfaces;

import android.content.Context;

import com.barranquero.manageproductsrecycler.model.Product;

import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */
public interface ProductPresenter {
    void getAllProducts();
    Product getProduct(int id);
    void deleteProduct(Product product);
    void onDestroy();

    void addProduct(Product product);

    //void deleteFinallyProduct(Product product);


    interface View {
        void showProducts(List<Product> products);
        void showEmptyState(boolean show);
        void showMessage(String message);
        void showMessageDelete(Product product);

        Context getContext();
    }
}
