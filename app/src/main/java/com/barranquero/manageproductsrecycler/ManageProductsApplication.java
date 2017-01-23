package com.barranquero.manageproductsrecycler;

import android.app.Application;

import com.barranquero.manageproductsrecycler.database.DatabaseHelper;
import com.barranquero.manageproductsrecycler.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ManageProductsApplication extends Application {
    public static ManageProductsApplication manageProductsApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        manageProductsApplication = this;
        DatabaseHelper.getInstance().open();
    }

    public static ManageProductsApplication getContext() {
        return manageProductsApplication;
    }
}