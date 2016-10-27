package com.barranquero.manageproductsrecycler;

import android.app.Application;

import com.barranquero.manageproductsrecycler.model.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class ManageProducts_Application extends Application {
    private ArrayList<Product> products = new ArrayList<Product>();

    @Override
    public void onCreate() {
        super.onCreate();
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
        saveProduct(new Product("Lizipadol", "Pastillas para chupar", "Boehringer", "20mg", 13.01, 51, R.drawable.lizipadol));
    }

    private void saveProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts(int option) {
        // Collections.sort(products, Product.PRICE_COMPARATOR);
        switch (option) {
            case 0:
                // Unsorted list
                break;
            case 1:
                // Sorted by price list
                Collections.sort(products, (p1, p2) -> Double.compare(p1.getmPrice(), p2.getmPrice()));
                break;
            case 2:
                // Sorted by stock list
                Collections.sort(products, (p1, p2) -> p1.getmStock() - p2.getmStock());
                break;
            case 3:
                // Alphabetically sorted list
                Collections.sort(products);
                break;
        }
        return this.products;
    }

    public void addProduct(Product p) {
        saveProduct(p);
    }
}