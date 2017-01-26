package com.barranquero.manageproductsrecycler.presenter;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.barranquero.manageproductsrecycler.database.DatabaseManager;
import com.barranquero.manageproductsrecycler.dialog.ConfirmDialog;
import com.barranquero.manageproductsrecycler.interfaces.ProductPresenter;
import com.barranquero.manageproductsrecycler.model.Product;

import java.util.List;

/**
 * Class that implements the ProductPresenter interface
 */
public class ProductPresenterImpl implements ProductPresenter, ConfirmDialog.OnDeleteProductListener {
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
        new AsyncTask<Void, Void, List<Product>>() {
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
                return DatabaseManager.getInstance().getAllProducts();
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
        }.execute();
    }

    @Override
    public Product getProduct(int id) {
        return DatabaseManager.getInstance().getProduct(id);
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
        DatabaseManager.getInstance().addProduct(product);
        //view.showProducts(DatabaseManager.getInstance().getAllProducts());
        getAllProducts();
    }

    /* Example implementation to delete product once the SnackBar times out
    @Override
    public void deleteFinallyProduct(Product product) {
        repository.deleteProduct(product);
        getAllProducts();
    }*/
}
