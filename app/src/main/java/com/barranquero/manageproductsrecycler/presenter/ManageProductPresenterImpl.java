package com.barranquero.manageproductsrecycler.presenter;

import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.database.DatabaseManager;
import com.barranquero.manageproductsrecycler.interfaces.ManageProductPresenter;
import com.barranquero.manageproductsrecycler.model.Product;

/**
 * Created by usuario on 12/12/16.
 */
public class ManageProductPresenterImpl implements ManageProductPresenter {
    ManageProductPresenter.View myView;

    public ManageProductPresenterImpl(ManageProductPresenter.View view) {
        this.myView = view;
    }

    @Override
    public void saveProduct(Product product) {
        DatabaseManager myRepository = DatabaseManager.getInstance();
        if (myRepository.getAllProducts().contains(product))
            myView.showMessage(Integer.toString(R.string.already_exists));
        else
            myRepository.addProduct(product);
    }

    @Override
    public void updateProduct(Product oldOne, Product newOne) {
        DatabaseManager.getInstance().deleteProduct(oldOne);
        DatabaseManager.getInstance().addProduct(newOne);
    }

    @Override
    public void onDestroy() {
        this.myView = null;
    }
}
