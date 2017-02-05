package com.barranquero.manageproductsrecycler.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author José Antonio Barranquero Fernández
 */

public class Pharmacy implements Parcelable {
    private int id;
    private String cif;
    private String address;
    private String phone;
    private String email;

    public Pharmacy() {
    }

    public Pharmacy(String address, String cif, String email, String phone) {
        this.address = address;
        this.cif = cif;
        this.email = email;
        this.phone = phone;
    }

    protected Pharmacy(Parcel in) {
        id = in.readInt();
        cif = in.readString();
        address = in.readString();
        phone = in.readString();
        email = in.readString();
    }

    public static final Creator<Pharmacy> CREATOR = new Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel in) {
            return new Pharmacy(in);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(cif);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(email);
    }
}
