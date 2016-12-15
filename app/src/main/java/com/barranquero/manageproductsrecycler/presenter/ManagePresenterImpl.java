package com.barranquero.manageproductsrecycler.presenter;

import com.barranquero.manageproductsrecycler.interfaces.ManagePresenter;
import com.barranquero.manageproductsrecycler.interfaces.ProductPresenter;

/**
 * Created by usuario on 12/12/16.
 */
public class ManagePresenterImpl implements ManagePresenter{
    ManagePresenter.View myView;

    public ManagePresenterImpl(ManagePresenter.View view) {
        this.myView = view;
    }

    @Override
    public void saveProduct() {

    }

    @Override
    public void editProduct() {

    }
}
