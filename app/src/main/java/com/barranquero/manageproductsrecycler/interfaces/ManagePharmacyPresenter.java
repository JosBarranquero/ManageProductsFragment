package com.barranquero.manageproductsrecycler.interfaces;

import com.barranquero.manageproductsrecycler.model.Pharmacy;

/**
 * Interface which implements methods to be implemented by ManagePharmacyFragment
 */
public interface ManagePharmacyPresenter {
    void savePharmacy(Pharmacy pharmacy);
    void updatePharmacy(Pharmacy oldOne, Pharmacy newOne);
    void onDestroy();

    interface View {
        void showMessage(String message);
    }
}
