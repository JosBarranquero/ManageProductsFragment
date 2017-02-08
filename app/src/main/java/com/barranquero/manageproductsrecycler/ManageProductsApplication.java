package com.barranquero.manageproductsrecycler;

import android.app.Application;

import com.barranquero.manageproductsrecycler.database.DatabaseHelper;


public class ManageProductsApplication extends Application {
    public static ManageProductsApplication manageProductsApplication;

    public ManageProductsApplication() {
        manageProductsApplication = this;
    }

    /*@Override
    public void onCreate() {
        super.onCreate();
        manageProductsApplication = this;
        DatabaseHelper.getInstance().open();
    }*/

    public static ManageProductsApplication getContext() {
        return manageProductsApplication;
    }
}