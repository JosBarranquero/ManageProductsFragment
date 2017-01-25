package com.barranquero.manageproductsrecycler;

import com.barranquero.manageproductsrecycler.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario on 9/12/16.
 */

public class ProductRepository {
    private static ProductRepository repository;
    private List<Product> products;

    private ProductRepository() {
        /*products = new ArrayList<>();
        saveProduct(new Product("Ibuprofeno", "Imprescindible", "Cinfa", "600mg", 7.50, 23, R.drawable.ibuprofeno));
        saveProduct(new Product("Metamizol", "Bueno para malestar general", "Nolotil", "575mg", 13.99, 4, R.drawable.nolotil));
        saveProduct(new Product("Cis-control", "Para molestias urinarias", "Cranberola", "36mg", 25.10, 10, R.drawable.cranberola));
        saveProduct(new Product("Lizipadol", "Pastillas para chupar", "Boehringer", "20mg", 13.01, 51, R.drawable.lizipadol));
        saveProduct(new Product("Ibuprofeno", "Imprescindible", "Cinfa", "600mg", 7.50, 23, R.drawable.ibuprofeno));
        saveProduct(new Product("Metamizol", "Bueno para malestar general", "Nolotil", "575mg", 13.99, 4, R.drawable.nolotil));
        saveProduct(new Product("Cis-control", "Para molestias urinarias", "Cranberola", "36mg", 25.10, 10, R.drawable.cranberola));
        saveProduct(new Product("Lizipadol", "Pastillas para chupar", "Boehringer", "20mg", 13.01, 51, R.drawable.lizipadol));
        saveProduct(new Product("Ibuprofeno", "Imprescindible", "Cinfa", "600mg", 7.50, 23, R.drawable.ibuprofeno));
        saveProduct(new Product("Metamizol", "Bueno para malestar general", "Nolotil", "575mg", 13.99, 4, R.drawable.nolotil));
        saveProduct(new Product("Cis-control", "Para molestias urinarias", "Cranberola", "36mg", 25.10, 10, R.drawable.cranberola));
        saveProduct(new Product("Lizipadol", "Pastillas para chupar", "Boehringer", "20mg", 13.01, 51, R.drawable.lizipadol));*/
    }

    public static ProductRepository getInstance() {
        if (repository == null)
            repository = new ProductRepository();
        return repository;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    private void saveProduct(Product p) {
        products.add(p);
    }

    public void addProduct(Product p) {
        saveProduct(p);
    }

    public Product getProduct(int id) {
        return null;
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

    public void updateProduct(Product product) {

    }
}
