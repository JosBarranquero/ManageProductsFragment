package com.barranquero.manageproductsrecycler.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.barranquero.manageproductsrecycler.R;
import com.barranquero.manageproductsrecycler.model.Pharmacy;

/**
 * Created by usuario on 30/01/17
 * ManageProductsFragment
 */

public class PharmacyAdapter extends CursorAdapter {

    class PharmacyHolder {
        TextView txvCif, txvPhone, txvAddress, txvEmail;
    }

    public PharmacyAdapter(Context context/*, int layout*/, Cursor c, /*String[] from, int[] to, */int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //return super.newView(context, cursor, parent);
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rootView = layoutInflater.inflate(R.layout.item_list_pharmacy, parent, false);

        PharmacyHolder holder = new PharmacyHolder();
        holder.txvCif = (TextView) rootView.findViewById(R.id.txvCif);
        holder.txvAddress = (TextView) rootView.findViewById(R.id.txvAddress);
        holder.txvEmail = (TextView) rootView.findViewById(R.id.txvEmail);
        holder.txvPhone = (TextView) rootView.findViewById(R.id.txvPhone);
        rootView.setTag(holder);

        return rootView;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //super.bindView(view, context, cursor);
        PharmacyHolder holder = (PharmacyHolder) view.getTag();
        holder.txvCif.setText(cursor.getString(1));
        holder.txvAddress.setText(cursor.getString(2));
        holder.txvPhone.setText(cursor.getString(3));
        holder.txvEmail.setText(cursor.getString(4));
    }

    @Override
    public Object getItem(int position) {
        //return super.getItem(position);
        getCursor().moveToPosition(position);
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setCif(getCursor().getString(1));
        pharmacy.setAddress(getCursor().getString(2));
        pharmacy.setPhone(getCursor().getString(3));
        pharmacy.setEmail(getCursor().getString(4));

        return pharmacy;
    }
}
