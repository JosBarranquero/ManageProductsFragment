package com.barranquero.manageproductsrecycler.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.barranquero.manageproductsrecycler.interfaces.IProduct;

import java.util.Comparator;
import java.util.Currency;
import java.util.Locale;

/**
 * Model class
 * If we want to order by different fields, we don't use the Comparable interface, we need to create Comparator fields
 */
public class Product implements Comparable<Product>, Parcelable, IProduct {
    private int mId;
    private String mName;
    private String mDescription;
    private String mBrand;
    private String mDosage;
    private double mPrice;
    private int mStock;
    private int mImage;
    private int mCategory;

    public static final Comparator<Product> NAME_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.mName.compareTo(p2.mName);
        }
    };

    public static final Comparator<Product> PRICE_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return Double.compare(p1.getmPrice(), p2.getmPrice());
        }
    };
    public static final Comparator<Product> STOCK_COMPARATOR = new Comparator<Product>() {
        @Override
        public int compare(Product p1, Product p2) {
            return p1.getmStock() - p2.getmStock();
        }
    };

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };


    public Product(String mName, String mDescription, String mBrand, String mDosage, double mPrice, int mStock, int mImage, int mCategory) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mBrand = mBrand;
        this.mDosage = mDosage;
        this.mPrice = mPrice;
        this.mStock = mStock;
        this.mImage = mImage;
        this.mCategory = mCategory;
    }

    public Product() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeString(mBrand);
        parcel.writeString(mDosage);
        parcel.writeDouble(mPrice);
        parcel.writeInt(mStock);
        parcel.writeInt(mImage);
    }

    protected Product(Parcel in) {
        mId = in.readInt();
        mName = in.readString();
        mDescription = in.readString();
        mBrand = in.readString();
        mDosage = in.readString();
        mPrice = in.readDouble();
        mStock = in.readInt();
        mImage = in.readInt();
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmBrand() {
        return mBrand;
    }

    public void setmBrand(String mBrand) {
        this.mBrand = mBrand;
    }

    public String getmDosage() {
        return mDosage;
    }

    public void setmDosage(String mDosage) {
        this.mDosage = mDosage;
    }

    public double getmPrice() {
        return mPrice;
    }

    public String getFormattedPrice() {
        return String.format(Currency.getInstance(Locale.getDefault()).getSymbol() + "%s", mPrice);
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public int getmStock() {
        return mStock;
    }

    public String getFormattedStock() {
        return String.format(Locale.getDefault(), "%d u.", mStock);
    }

    public void setmStock(int mStock) {
        this.mStock = mStock;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString() {
        return "Product { " + Integer.toString(mId) + ", " + mName + ", " + mDescription + ", " + mBrand + ", " + Double.toString(mPrice) + ", " + Integer.toString(mStock) + "}";
    }

    /* Two products are the same, when they have the same name, brand and dosage */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Product product = (Product) obj;

        if (!mName.equals(product.mName)) return false;
        if (!mBrand.equals(product.mBrand)) return false;
        return mDosage.equals(product.mDosage);
    }

    @Override
    public int compareTo(Product p) {
        if (this.getmName().compareTo(p.getmName()) == 0)
            return this.getmBrand().compareTo(p.getmBrand());
        else
            return this.getmName().compareTo(p.getmName());
    }


    public int getmCategory() {
        return mCategory;
    }

    public void setmCategory(int mCategory) {
        this.mCategory = mCategory;
    }
}