package com.barranquero.manageproductsrecycler.presenter;

import com.barranquero.manageproductsrecycler.interfaces.ManagePharmacyPresenter;
import com.barranquero.manageproductsrecycler.model.Pharmacy;

/**
 * @author José Antonio Barranquero Fernández
 * @version 1.0
 *          ManageProductsFragment
 */

public class ManagePharmacyPresenterImpl implements ManagePharmacyPresenter {
    ManagePharmacyPresenter.View myView;

    public ManagePharmacyPresenterImpl(ManagePharmacyPresenter.View view) {
        this.myView = view;
    }

    @Override
    public void savePharmacy(Pharmacy pharmacy) {
        /*DatabaseManager myRepository = DatabaseManager.getInstance();
        if (myRepository.getAllProducts().contains(pharmacy))
            myView.showMessage(Integer.toString(R.string.already_exists));
        else
            myRepository.addPharmacy(pharmacy);*/
    }

    @Override
    public void updatePharmacy(Pharmacy oldOne, Pharmacy newOne) {
        //TODO DatabaseManager.getInstance().deletePharmacy(oldOne);
        //DatabaseManager.getInstance().addPharmacy(newOne);
    }

    @Override
    public void onDestroy() {
        this.myView = null;
    }
}
