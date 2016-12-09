package com.barranquero.manageproductsrecycler.dialog;

import android.support.v4.app.DialogFragment;

import com.barranquero.manageproductsrecycler.model.Product;

/**
 * Created by usuario on 9/12/16.
 */

public class ConfirmDialog extends DialogFragment {
    private OnDeleteProductListener deleteProductListener;

    public interface OnDeleteProductListener {
        void deleteProduct(Product product);
    }
}
