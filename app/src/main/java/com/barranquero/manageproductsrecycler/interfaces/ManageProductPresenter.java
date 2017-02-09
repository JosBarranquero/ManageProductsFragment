package com.barranquero.manageproductsrecycler.interfaces;

import android.content.Context;

import com.barranquero.manageproductsrecycler.model.Product;

/**
 * Interface which implements methods to be implemented by ManageProductsFragment
 */
public interface ManageProductPresenter {
    void saveProduct(Product product);
    void updateProduct(Product oldOne, Product newOne);
    void onDestroy();

    interface View {
        void showMessage(String message);

        Context getContext();
    }
}
