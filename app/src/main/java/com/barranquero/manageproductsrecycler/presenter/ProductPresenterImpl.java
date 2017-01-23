package com.barranquero.manageproductsrecycler.presenter;

import com.barranquero.manageproductsrecycler.ProductRepository;
import com.barranquero.manageproductsrecycler.database.DatabaseManager;
import com.barranquero.manageproductsrecycler.dialog.ConfirmDialog;
import com.barranquero.manageproductsrecycler.interfaces.ProductPresenter;
import com.barranquero.manageproductsrecycler.model.Product;

/**
 * Class that implements the ProductPresenter interface
 */
public class ProductPresenterImpl implements ProductPresenter, ConfirmDialog.OnDeleteProductListener {
    private ProductPresenter.View view;
    private ProductRepository repository;

    public ProductPresenterImpl(ProductPresenter.View view) {
        this.view = view;
        this.repository = ProductRepository.getInstance();
    }

    @Override
    public void loadProducts() {
        if (DatabaseManager.getInstance().getProducts().isEmpty()) {
            view.showEmptyState(true);
        } else {
            view.showEmptyState(false);
            view.showProducts(DatabaseManager.getInstance().getProducts());
        }
    }

    @Override
    public Product getProduct(int id) {
        return DatabaseManager.getInstance().getProduct(id);
    }

    @Override
    public void deleteProduct(Product product) {
        //repository.deleteProduct(product);

        //Depends on the implementation
        //loadProducts();
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
        view.showProducts(DatabaseManager.getInstance().getProducts());
    }

    /* Example implementation to delete product once the SnackBar times out
    @Override
    public void deleteFinallyProduct(Product product) {
        repository.deleteProduct(product);
        loadProducts();
    }*/
}
