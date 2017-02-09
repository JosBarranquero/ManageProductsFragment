package com.barranquero.manageproductsrecycler.presenter;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.barranquero.manageproductsrecycler.cursor.ProductCursorLoader;
import com.barranquero.manageproductsrecycler.dialog.ConfirmDialog;
import com.barranquero.manageproductsrecycler.interfaces.ProductPresenter;
import com.barranquero.manageproductsrecycler.model.Product;
import com.barranquero.manageproductsrecycler.provider.ManageProductContract;

import static com.barranquero.manageproductsrecycler.provider.ManageProductProvider.PRODUCT;

//import com.barranquero.manageproductsrecycler.database.DatabaseManager;

/**
 * Class that implements the ProductPresenter interface
 */
public class ProductPresenterImpl implements ProductPresenter, ConfirmDialog.OnDeleteProductListener, LoaderManager.LoaderCallbacks<Cursor> {
    private ProductPresenter.View view;
    //private ProductRepository repository;

    public ProductPresenterImpl(ProductPresenter.View view) {
        this.view = view;
        //this.repository = ProductRepository.getInstance();
    }

    @Override
    /*public void getAllProducts() {
        if (DatabaseManager.getInstance().getAllProducts().isEmpty()) {
            view.showEmptyState(true);
        } else {
            view.showEmptyState(false);
            view.showProducts(DatabaseManager.getInstance().getAllProducts());
        }
    }*/
    public void getAllProducts() {
        /*new AsyncTask<Void, Void, List<Product>>() {
            ProgressDialog progressDialog = new ProgressDialog(view.getContext());

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog.setMessage("Loading...");
                progressDialog.show();
            }

            @Override
            protected List<Product> doInBackground(Void... params) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null; //DatabaseManager.getInstance().getAllProducts();
            }

            @Override
            protected void onPostExecute(List<Product> products) {
                if (progressDialog != null)
                    progressDialog.dismiss();
                view.showProducts(products);
            }

            @Override
            protected void onCancelled() {
                super.onCancelled();
            }
        }.execute();*/
        ((Activity) view.getContext()).getLoaderManager().initLoader(PRODUCT, null, this);
    }

    @Override
    public Product getProduct(int id) {
        return null; //DatabaseManager.getInstance().getProduct(id);
    }

    @Override
    public void deleteProduct(Product product) {
        //repository.deleteProduct(product);

        //Depends on the implementation
        //getAllProducts();
        /*view.deleteProduct();
        if (view.getAdapter().isEmpty()) {
            view.showEmptyState(true);
        }*/
        view.showMessageDelete(product);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void addProduct(Product product) {
        //DatabaseManager.getInstance().addProduct(product);
        //view.showProducts(DatabaseManager.getInstance().getAllProducts());
        getAllProducts();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Loader<Cursor> loader = null;
        switch (id) {
            case PRODUCT:
                loader = new ProductCursorLoader(view.getContext());
                break;
        }
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        view.setCursor(data);
        view.getCursor().setNotificationUri(view.getContext().getContentResolver(), ManageProductContract.CategoryEntry.CONTENT_URI);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        view.setCursor(null);
    }

    /* Example implementation to delete product once the SnackBar times out
    @Override
    public void deleteFinallyProduct(Product product) {
        repository.deleteProduct(product);
        getAllProducts();
    }*/
}
